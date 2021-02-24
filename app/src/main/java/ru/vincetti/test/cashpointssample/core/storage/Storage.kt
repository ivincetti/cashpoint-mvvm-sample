package ru.vincetti.test.cashpointssample.core.storage

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.data.CashPointShortDetails
import ru.vincetti.test.cashpointssample.core.data.PointsResult
import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner

interface Storage {

    suspend fun getPointsForMap(
        latLng: LatLng,
        radius: Double
    ): PointsResult

    fun getPointForMapById(id: String): CashPointShortDetails?

    fun getPointDetailById(id: String): CashPointDetails?

    fun getPartnerById(id: String): Partner?
}
