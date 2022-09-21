package com.example.shift.usecases

import android.text.method.CharacterPickerDialog
import android.util.Log
import javax.inject.Inject

class ValidationName @Inject constructor() {

    fun checkValidation(text: String?): Boolean{
        if(text != null && text.isNotEmpty() && text.length > 3){
            if(text[0] != text[0].uppercaseChar()){
                return false
            }else{
                for(i in 1..text.length-1){
                    if(text[i] !in 'a'..'z' && text[i] !in 'а'..'я'){
                        return false
                    }
                }
                return true
            }
        }else{
            return false
        }
    }
}