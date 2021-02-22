package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

interface PointsModel {

    fun getPoints(point: LatLng, radius: Double): List<DepositPoint>

    fun getPointById(id: String): DepositPoint?
}
