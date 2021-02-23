package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.core.data.CashPoint
import ru.vincetti.test.cashpointssample.core.data.Point
import ru.vincetti.test.cashpointssample.core.data.PointsResult
import ru.vincetti.test.cashpointssample.utils.GeoMath
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(
    private val storage: Storage
) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<CashPoint>()
    val needToNavigateToDetails = SingleLiveEvent<String>()
    val needToBlockUser = SingleLiveEvent<Boolean>()

    val points = SingleLiveEvent<List<Point>>()

    fun checkArea(map: GoogleMap) {
        needToBlockUser.value = true
        val radius = GeoMath.getMapVisibleRadius(map.projection.visibleRegion)
        val point = map.cameraPosition.target
        viewModelScope.launch {
            when (val result = storage.getPointsForMap(point.latitude, point.longitude, radius)) {
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
                storage.getPointForMapById(id)?.let { point ->
                    needToShowBottomSheet.value = point
                }
            }
        }
    }

    fun onDetailsSheetClicked() {
        needToShowBottomSheet.value?.let {
            needToNavigateToDetails.value = it.id
        }
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
