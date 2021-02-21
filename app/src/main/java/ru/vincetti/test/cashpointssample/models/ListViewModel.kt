package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.model.Marker
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class ListViewModel(pointsModel: PointsModel) : ViewModel() {

    val needToShowBottomSheet = SingleLiveEvent<Boolean>()

    val list = pointsModel.getPoints()

    fun onMarkerClicked(marker: Marker?) {
        marker?.let {
            needToShowBottomSheet.value = true
        }
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
