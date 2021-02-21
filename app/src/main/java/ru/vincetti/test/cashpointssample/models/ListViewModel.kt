package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class ListViewModel : ViewModel() {

    val list = listOf(
        CashPoint(LatLng(-34.0, 151.0), "Sydney"),
        CashPoint(LatLng(-40.0, 16.0), "f"),
        CashPoint(LatLng(15.0, -20.0), "213"),
        CashPoint(LatLng(40.0, -10.0), "ddd"),
    )
}
