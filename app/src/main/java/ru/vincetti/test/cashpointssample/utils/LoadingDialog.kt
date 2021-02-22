package ru.vincetti.test.cashpointssample.utils

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.vincetti.test.cashpointssample.R


class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = MaterialAlertDialogBuilder(requireActivity())
        dialogBuilder.setView(R.layout.dialog_loading)

        return dialogBuilder.create()
    }

    companion object {
        const val FRAGMENT_TAG = "LoadingDialog"

        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }
}
