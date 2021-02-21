package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListViewModel(pointsModel: PointsModel) : ViewModel() {

    val list = pointsModel.getPoints()
}

class ListViewModelFactory(
    private val pointsModel: PointsModel
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(pointsModel) as T
    }
}
