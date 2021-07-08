package com.example.intervention_plombiers

import android.app.AlertDialog
import android.app.Dialog

import android.content.DialogInterface
import android.os.Bundle


class SelectDateDialogFragment: androidx.fragment.app.DialogFragment() {
    interface SelectDateListener {


        fun onDialogPositiveClick()
    }
    var  listener: SelectDateListener? =null



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder=AlertDialog.Builder(activity)

        val inflater= activity?.layoutInflater


        return builder.create()


    }
}