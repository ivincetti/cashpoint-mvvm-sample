package ru.vincetti.test.cashpointssample.utils

import android.Manifest
import android.app.Dialog
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vincetti.test.cashpointssample.R

object PermissionUtils {

    const val LOCATION_PERMISSION_REQUEST_CODE = 1

    /**
     * Requests the fine location permission. If a rationale with an additional explanation should
     * be shown to the user, displays a dialog that triggers the request.
     */
    fun requestPermission(
        activity: FragmentActivity,
        requestId: Int,
        permission: String
    ) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            RationaleDialog.newInstance(requestId)
                .show(activity.supportFragmentManager, RationaleDialog.FRAGMENT_TAG)
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestId)
        }
    }

    class RationaleDialog : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val requestCode = checkNotNull(arguments?.getInt(ARGUMENT_PERMISSION_REQUEST_CODE))

            return MaterialAlertDialogBuilder(requireActivity())
                .setMessage(R.string.location_permission_rationale_message)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        requestCode
                    )
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
        }

        companion object {

            const val FRAGMENT_TAG = "RationaleDialog"
            private const val ARGUMENT_PERMISSION_REQUEST_CODE = "requestCode"

            fun newInstance(requestCode: Int): RationaleDialog {
                val arguments = Bundle().also {
                    it.putInt(ARGUMENT_PERMISSION_REQUEST_CODE, requestCode)
                }
                return RationaleDialog().also { it.arguments = arguments }
            }
        }
    }
}
