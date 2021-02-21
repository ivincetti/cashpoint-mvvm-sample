package ru.vincetti.test.cashpointssample.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import ru.vincetti.test.cashpointssample.utils.GeoMath
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(private val pointsModel: PointsModel) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<CashPoint>()
    val needToNavigateToDetails = SingleLiveEvent<Boolean>()

    val points = SingleLiveEvent<List<CashPoint>>()

    fun checkArea(map: GoogleMap) {
        val radius = GeoMath.getMapVisibleRadius(map.projection.visibleRegion)
        val point = map.cameraPosition.target
        Log.d("OLOLO", "area changed to $point with radius = $radius")
        points.value = pointsModel.getPoints(point, radius)
    }

    fun onMarkerClicked(marker: Marker?) {
        marker?.let {
            val markerId = it.tag as? Int
            markerId?.let { id ->
                pointsModel.findPointById(id)?.let { point ->
                    needToShowBottomSheet.value = point
                }
            }
        }
    }

    fun onDetailsSheetClicked() {
        needToNavigateToDetails.value = true
    }
}

class ListViewModelFactory(
    private val pointsModel: PointsModel
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(pointsModel) as T
    }
}
