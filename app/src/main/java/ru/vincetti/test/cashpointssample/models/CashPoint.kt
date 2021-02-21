package ru.vincetti.test.cashpointssample.models

import com.google.android.gms.maps.model.LatLng

data class CashPoint(
    val latLong: LatLng,
    val name: String
)
