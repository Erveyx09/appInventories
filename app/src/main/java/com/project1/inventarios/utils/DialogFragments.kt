package com.project1.inventarios.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment


class DialogFragments: DialogFragment(){

    private lateinit var dialogListener: DialogListener
    private var ids: Int =  0
    private var position: Int =  0


    var mContext: Context? = null
    fun MyDialogFragment() {
        mContext = activity
    }

    fun setListener(dialogListener: DialogListener){
        this.dialogListener = dialogListener
    }

    fun setIds(ids:Int,position:Int){
        this.ids = ids
        this.position = position
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Really?")
        alertDialogBuilder.setMessage("Are you sure?")
        //null should be your on click listener
        alertDialogBuilder.setPositiveButton("OK", DialogInterface.OnClickListener {
                dialog, which ->
            dialogListener.onClickYes(ids,position)
        })
        alertDialogBuilder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        return alertDialogBuilder.create()
    }

}