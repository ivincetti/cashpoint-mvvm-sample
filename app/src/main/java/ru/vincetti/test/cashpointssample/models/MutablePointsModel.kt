package ru.vincetti.test.cashpointssample.models

interface MutablePointsModel : PointsModel {

    fun setPoints(list: List<CashPoint>)
}
