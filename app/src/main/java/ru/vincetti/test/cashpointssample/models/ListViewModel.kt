package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val pointsModel: PointsModel = PointsModelImpl()

    val list = pointsModel.getPoints()
}
