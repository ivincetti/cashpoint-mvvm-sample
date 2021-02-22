package ru.vincetti.test.cashpointssample.models

interface MutablePointsModel : PointsModel {

    fun setPoint(list: List<CashPoint>)
}
