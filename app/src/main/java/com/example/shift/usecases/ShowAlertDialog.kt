package com.example.shift.usecases

import android.app.AlertDialog
import android.content.Context
import javax.inject.Inject

class ShowAlertDialog @Inject constructor() {

    fun showDialog(message: String, context: Context){
        val dialog = AlertDialog.Builder(context)
            .setCancelable(true)
            .setTitle("Welcome")
            .setMessage(message)
            .setNegativeButton("Ok"){_,_->

            }
            .create()

        dialog.show()

    }
}