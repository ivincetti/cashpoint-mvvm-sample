package ru.vincetti.test.cashpointssample.models

import androidx.lifecycle.ViewModel
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class MainViewModel : ViewModel() {

    val permissionGranted = SingleLiveEvent<Boolean>()

    fun enableLocation() {
        permissionGranted.value = true
    }
}
