package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng

interface PointsModel {

    fun getPoints(point: LatLng, radius: Double): List<CashPoint>

    fun getPointById(id: String): CashPoint?
}
