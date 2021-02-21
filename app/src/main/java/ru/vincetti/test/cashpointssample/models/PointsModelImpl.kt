package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class PointsModelImpl @Inject constructor() : PointsModel {

    private val list = listOf(
        CashPoint(1, LatLng(-34.0, 151.0), "Sydney"),
        CashPoint(2, LatLng(-40.0, 16.0), "f"),
        CashPoint(3, LatLng(15.0, -20.0), "213"),
        CashPoint(4, LatLng(40.0, -10.0), "ddd"),
    )

    override fun getPoints(): List<CashPoint> = list
}
