package ru.vincetti.test.cashpointssample.utils

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

object GeoConstants {

    val MOSCOW = CameraPosition.Builder().target(LatLng(55.748931, 37.63256))
        .zoom(14f)
        .bearing(0f)
        .tilt(25f)
        .build()
}
