package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.model.Marker
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(private val pointsModel: PointsModel) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<CashPoint>()
    val needToNavigateToDetails = SingleLiveEvent<Boolean>()

    private val _points = MutableLiveData<List<CashPoint>>()
    val points: LiveData<List<CashPoint>>
        get() = _points

    private val list = pointsModel.getPoints()

    fun onMapReady() {
        _points.value = list
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
