package com.example.shift.usecases

import android.util.Log
import javax.inject.Inject

class ValidationDate @Inject constructor() {

    fun checkValidation(date: String?): Boolean{
        if(date != null && date.isNotEmpty() && date.length == 10){
            for(i in 0..date.length-1){
                if(date[i] == '.' && i == 0){
                    return false
                }else if(date[i] != '.' && (i == 2 || i == 5)){
                    return false
                }else if(date[i] == '.'){
                    continue
                }else if(date[i] !in '0'..'9'){
                    return false
                }
            }
            return true
        }
        return false

    }
}