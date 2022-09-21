package com.example.shift.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shift.usecases.ShowAlertDialog
import javax.inject.Inject

class MainViewModel : ViewModel() {

    var name: String? = ""

    fun saveName(text: String?){
        if(text != null){
            name = text
        }
    }
}