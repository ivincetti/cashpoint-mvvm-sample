package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.launch
import ru.vincetti.test.cashpointssample.core.data.CashPoint
import ru.vincetti.test.cashpointssample.core.data.CashPointShortDetails
import ru.vincetti.test.cashpointssample.core.data.PointsResult
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.utils.GeoConstants
import ru.vincetti.test.cashpointssample.utils.GeoMath
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(
    private val storage: Storage
) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<CashPointShortDetails>()
    val needToNavigateToDetails = SingleLiveEvent<String>()
    val needToBlockUser = SingleLiveEvent<Boolean>()
    val needToShowNetworkError = SingleLiveEvent<Boolean>()

    val mapPoint = SingleLiveEvent<CameraPosition>()

    private val _points = MutableLiveData<List<CashPoint>?>()
    val points: LiveData<List<CashPoint>?>
        get() = _points

    private var isMapReady = false

    private var zoom: Float = GeoConstants.DEFAULT_ZOOM
    private var latLng = LatLng(GeoConstants.MOSCOW_LAT, GeoConstants.MOSCOW_LNG)

    fun getMapStart() {
        isMapReady = false
        _points.value = null
    }

    fun mapReady() {
        isMapReady = true
        mapPoint.value = CameraPosition.Builder()
            .target(latLng)
            .zoom(zoom)
            .build()
    }

    fun checkArea(map: GoogleMap) {
        needToBlockUser.value = true
        val radius = GeoMath.getMapVisibleRadius(map.projection.visibleRegion)
        val point = map.cameraPosition.target
        zoom = map.cameraPosition.zoom
        latLng = LatLng(point.latitude, point.longitude)
        viewModelScope.launch {
            when (val result = storage.getPointsForMap(latLng, radius)) {
                is PointsResult.ERROR -> {
                    needToShowNetworkError.value = true
                }
                is PointsResult.SUCCESS -> {
                    _points.value = result.list
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

    fun clickOnPointInList(id: String) {
        needToNavigateToDetails.value = id
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
