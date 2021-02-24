package ru.vincetti.test.cashpointssample.core.storage

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.data.PointsResult

interface Storage {

    suspend fun getPointsForMap(
        latLng: LatLng,
        radius: Double
    ): PointsResult

    suspend fun getPointById(id: String): CashPointDetails?
}
