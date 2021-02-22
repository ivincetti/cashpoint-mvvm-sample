package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.utils.GeoMath
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(
    private val storage: Storage
) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<CashPoint>()
    val needToNavigateToDetails = SingleLiveEvent<Boolean>()
    val needToBlockUser = SingleLiveEvent<Boolean>()

    val points = SingleLiveEvent<List<Point>>()

    fun checkArea(map: GoogleMap) {
        needToBlockUser.value = true
        val radius = GeoMath.getMapVisibleRadius(map.projection.visibleRegion)
        val point = map.cameraPosition.target
        viewModelScope.launch {
            when (val result = storage.getPoints(point.latitude, point.longitude, radius)) {
                is PointsResult.ERROR -> {
                }
                is PointsResult.SUCCESS -> {
                    points.value = result.list
                }
            }
            needToBlockUser.value = false
        }
    }

    fun onMarkerClicked(marker: Marker?) {
        marker?.let {
            val markerId = it.tag as? String
            markerId?.let { id ->
                storage.getPointById(id)?.let { point ->
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
    private val storage: Storage
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(storage) as T
    }
}
