package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class PointsModelImpl @Inject constructor() : MutablePointsModel {

    private var pointsList = listOf<CashPoint>()

    override fun setPoints(list: List<CashPoint>) {
        this.pointsList = list
    }

    override fun getPoints(point: LatLng, radius: Double): List<CashPoint> = pointsList

    override fun getPointById(id: String): CashPoint? {
        return pointsList.firstOrNull { it.id == id }
    }
}
