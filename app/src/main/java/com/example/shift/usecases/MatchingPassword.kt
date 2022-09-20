package com.example.shift.usecases

import android.util.Log
import javax.inject.Inject

class MatchingPassword @Inject constructor(){

    fun checkMatching(t1: String?, t2: String?): Boolean{
        if(t1 != null && t2 != null && t1.isNotEmpty() && t2.isNotEmpty()){
            return t1.equals(t2)
        }else{
            return false
        }

    }
}