package ru.vincetti.test.cashpointssample.core.data

import com.google.android.gms.maps.model.LatLng

data class CashPoint(
    val id: String,
    val latLong: LatLng,
    val address: String
)
