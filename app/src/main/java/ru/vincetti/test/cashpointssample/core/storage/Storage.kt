package ru.vincetti.test.cashpointssample.core.storage

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import ru.vincetti.test.cashpointssample.core.data.CashPoint
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.data.PointsResult

interface Storage {

    suspend fun getPointsForMap(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult

    fun getPointForMapById(id: String): CashPoint?

    fun getPointDetailById(id: String): CashPointDetails?

    fun getPartnerById(id: String): Partner?
}
