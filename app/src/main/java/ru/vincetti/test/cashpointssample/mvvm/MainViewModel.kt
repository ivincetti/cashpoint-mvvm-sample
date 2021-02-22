package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.ViewModel
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class MainViewModel : ViewModel() {

    val permissionGranted = SingleLiveEvent<Boolean>()

    fun enableLocation() {
        permissionGranted.value = true
    }
}
