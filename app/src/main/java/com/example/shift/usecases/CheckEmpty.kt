package com.example.shift.usecases

import javax.inject.Inject

class CheckEmpty @Inject constructor() {

    fun checkEmptyField(name: String, secondName: String, date: String, password: String, password2: String) : Boolean{
        if(name.isNotEmpty() && secondName.isNotEmpty() && date.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()){
            return true
        }else{
            return false
        }
    }
}