package com.example.shift.usecases

import javax.inject.Inject

class ValidationPassword @Inject constructor(){

    fun checkValidation(password: String?): Boolean{
        if(password != null && password.isNotEmpty() && password.length > 3){
            for(char in password){
                if(char != char.uppercaseChar()){
                    return false
                }
            }
            return true
        }else{
            return false
        }
    }
}