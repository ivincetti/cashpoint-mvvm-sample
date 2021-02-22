package ru.vincetti.test.cashpointssample.core.storage.repo

import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

interface MutablePointsRepo : PointsRepo {

    fun setPoints(list: List<DepositPoint>)
}
