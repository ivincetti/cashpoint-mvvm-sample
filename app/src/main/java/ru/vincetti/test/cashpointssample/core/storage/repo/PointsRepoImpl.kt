package ru.vincetti.test.cashpointssample.core.storage.repo

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint
import javax.inject.Inject

class PointsRepoImpl @Inject constructor() : MutablePointsRepo {

    private var pointsList = listOf<DepositPoint>()

    override fun setPoints(list: List<DepositPoint>) {
        this.pointsList = list
    }

    override fun getPoints(point: LatLng, radius: Double): List<DepositPoint> = pointsList

    override fun getPointById(id: String): DepositPoint? {
        return pointsList.firstOrNull { it.externalId == id }
    }
}
