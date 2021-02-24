package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class MainViewModel : ViewModel() {

    val permissionGranted = SingleLiveEvent<Boolean>()

    private val _isNetworkConnected = MutableLiveData<Boolean>()
    val isNetworkConnected: LiveData<Boolean>
        get() = _isNetworkConnected

    init {
        _isNetworkConnected.value = false
    }

    fun setNetwork(isConnected: Boolean) {
        _isNetworkConnected.value = isConnected
    }

    fun enableLocation() {
        permissionGranted.value = true
    }
}
