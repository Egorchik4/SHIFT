package com.example.shift.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.shift.constants.Constants
import javax.inject.Inject

class SharedPrefStorage @Inject constructor(var context: Context) {
    var prefStorage: SharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
}