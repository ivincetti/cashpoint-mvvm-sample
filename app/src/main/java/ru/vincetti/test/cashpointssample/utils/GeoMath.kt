package ru.vincetti.test.cashpointssample.utils

import android.location.Location
import com.google.android.gms.maps.model.VisibleRegion

object GeoMath {

    fun getMapVisibleRadius(visibleRegion: VisibleRegion): Double {
        val diagonalDistance = FloatArray(1)
        val farLeft = visibleRegion.farLeft
        val nearRight = visibleRegion.nearRight
        Location.distanceBetween(
            farLeft.latitude,
            farLeft.longitude,
            nearRight.latitude,
            nearRight.longitude,
            diagonalDistance
        )
        return (diagonalDistance[0] / 2).toDouble()
    }
}
