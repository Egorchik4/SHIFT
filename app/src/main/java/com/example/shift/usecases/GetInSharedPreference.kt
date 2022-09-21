package com.example.shift.usecases

import android.content.SharedPreferences
import javax.inject.Inject

class GetInSharedPreference @Inject constructor(var prefget: SharedPreferences) {
    fun getInSharedPref(key: String): String? {
        return prefget.getString(key, null)
    }
}