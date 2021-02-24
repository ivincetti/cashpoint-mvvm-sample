package ru.vincetti.test.cashpointssample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtils(
    private val context: Context
) {

    fun isAvailable(): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork?.let { network ->
                connectivityManager.getNetworkCapabilities(network)?.let {
                    when {
                        it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        it.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                        else -> false
                    }
                } ?: false
            } ?: false
        } else {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }
}
