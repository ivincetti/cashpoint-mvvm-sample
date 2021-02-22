package ru.vincetti.test.cashpointssample.core.storage

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import ru.vincetti.test.cashpointssample.models.CashPoint
import ru.vincetti.test.cashpointssample.models.PointsResult

interface Storage {

    suspend fun getPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult

    fun getPointById(id: String): CashPoint?

    fun getPartnerById(id: String): Partner?
}
