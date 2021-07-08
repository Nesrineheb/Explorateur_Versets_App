package com.example.intervention_plombiers

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle


class ConfirmDeleteDialogueFragment: DialogFragment() {
    interface ConfirmDeleteListener {

        fun onDialogNegativeClick()
        fun onDialogPositiveClick()
    }
    var  listener: ConfirmDeleteListener? =null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(activity)
                builder.setMessage("supprimer ?")
            .setPositiveButton("Oui !",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogPositiveClick() })
            .setNegativeButton("Non ...",
                DialogInterface.OnClickListener { dialog, id -> listener?.onDialogNegativeClick() })

        return builder.create()
    }

}