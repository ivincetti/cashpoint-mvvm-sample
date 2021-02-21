package ru.vincetti.test.cashpointssample

import android.Manifest
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.vincetti.test.cashpointssample.models.MainViewModel
import ru.vincetti.test.cashpointssample.utils.PermissionUtils

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != PermissionUtils.LOCATION_PERMISSION_REQUEST_CODE) return

        if (PermissionUtils.isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            viewModel.enableLocation()
        }
    }

}
