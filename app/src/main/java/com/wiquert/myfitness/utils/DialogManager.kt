package com.wiquert.myfitness.utils

import android.app.AlertDialog
import android.content.Context
import com.wiquert.myfitness.R

class DialogManager {
    fun showDialog(context: Context, mId: Int, listener: Listener) {
        val builder = AlertDialog.Builder(context)
        var dialog: AlertDialog? = null
        builder.setTitle(R.string.alert)
        builder.setMessage(mId)
        builder.setPositiveButton(R.string.reset) { _, _ ->
            listener.onClick()
            dialog?.dismiss()
        }
        builder.setPositiveButton(R.string.cancel) { _, _ ->
            listener.onClick()
            dialog?.dismiss()
        }
        dialog = builder.create()
    }

    interface Listener {
        fun onClick()
    }
}