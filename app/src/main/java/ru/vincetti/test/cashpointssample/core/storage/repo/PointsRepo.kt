package ru.vincetti.test.cashpointssample.core.storage.repo

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

interface PointsRepo {

    fun getPoints(point: LatLng, radius: Double): List<DepositPoint>

    fun getPointById(id: String): DepositPoint?
}
