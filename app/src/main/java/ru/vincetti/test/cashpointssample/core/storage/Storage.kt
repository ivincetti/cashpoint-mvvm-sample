package ru.vincetti.test.cashpointssample.core.storage

import ru.vincetti.test.cashpointssample.models.CashPoint
import ru.vincetti.test.cashpointssample.models.PointsResult

interface Storage {

    suspend fun getDepositPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult

    fun findPointById(id: String): CashPoint?
}
