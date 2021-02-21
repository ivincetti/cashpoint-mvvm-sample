package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class PointsModelImpl @Inject constructor() : PointsModel {

    private val list = listOf(
        CashPoint(1, LatLng(55.72717, 37.61089), "Point1"),
        CashPoint(2, LatLng(55.77040, 37.57797), "Point2"),
        CashPoint(3, LatLng(55.76156, 37.68842), "Point3"),
        CashPoint(4, LatLng(55.75854, 37.64528), "Point4"),
    )

    override fun getPoints(point: LatLng, radius: Double): List<CashPoint> = list

    override fun findPointById(id: Int): CashPoint? {
        return list.firstOrNull { it.id == id }
    }
}
