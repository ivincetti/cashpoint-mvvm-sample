package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

interface MutablePointsModel : PointsModel {

    fun setPoints(list: List<DepositPoint>)
}
