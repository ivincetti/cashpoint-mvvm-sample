package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class PointsModelImpl @Inject constructor() : MutablePointsModel {

    private var list = listOf<CashPoint>()

    override fun getPoints(point: LatLng, radius: Double): List<CashPoint> = list

    override fun getPointById(id: String): CashPoint? {
        return list.firstOrNull { it.id == id }
    }

    override fun setPoint(list: List<CashPoint>) {
        this.list = list
    }
}
