package com.example.shift.screens.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shift.constants.Constants
import com.example.shift.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validationName: ValidationName,
    private val validationDate: ValidationDate,
    private val validationPassword: ValidationPassword,
    private val checkMatchingPassword: MatchingPassword,
    private val checkEmpty: CheckEmpty,
    private val putInSharedPreference: PutInSharedPreference,
    private val getInSharedPreference: GetInSharedPreference
): ViewModel(){

    // ответчает за проверку вводимого имени в реальном времени
    private val correctNameMut = MutableLiveData<Boolean>()
    val correctName: LiveData<Boolean> = correctNameMut

    // ответчает за проверку вводимой фамилии в реальном времени
    private val correctSecondNameMut = MutableLiveData<Boolean>()
    val correctSecondName: LiveData<Boolean> = correctSecondNameMut

    // ответчает за проверку вводимого даты рождения в реальном времени
    private val correctDateOfBirthdayMut = MutableLiveData<Boolean>()
    val correctDateOfBirthday: LiveData<Boolean> = correctDateOfBirthdayMut

    // ответчает за проверку вводимого пароля в реальном времени
    private val correctPasswordMut = MutableLiveData<Boolean>()
    val correctPassword: LiveData<Boolean> = correctPasswordMut

    // проверяет совпадение паролей
    private val matchingPasswordMut = MutableLiveData<Boolean>()
    val matchingPassword: LiveData<Boolean> = matchingPasswordMut

    // доступность для нажатия
    private val enableRegistrationButtonMut = MutableLiveData<Boolean>()
    val enableRegistrationButton: LiveData<Boolean> = enableRegistrationButtonMut

    private val userRegistrationMut = MutableLiveData<String?>()
    val userRegistration: LiveData<String?> = userRegistrationMut

    init{
        userRegistrationMut.value = getInSharedPreference.getInSharedPref(Constants.USER_NAME)
    }

    fun checkValidationName(name: String?){
        correctNameMut.value = validationName.checkValidation(name)
    }

    fun checkValidationSecondName(secondName: String?){
        correctSecondNameMut.value = validationName.checkValidation(secondName)
    }

    fun checkValidationDateOfBirthday(date: String?){
        correctDateOfBirthdayMut.value = validationDate.checkValidation(date)
    }

    fun checkValidationPassword(password: String?){
        correctPasswordMut.value = validationPassword.checkValidation(password)
    }

    fun checkMatchingPassword(password1: String?, password2: String?){
        matchingPasswordMut.value = checkMatchingPassword.checkMatching(password1, password2)
    }


    fun checkEnableRegistrationButton(name: String, secondName: String, date: String, password: String, password2: String){
        enableRegistrationButtonMut.value = checkEmpty.checkEmptyField(name,secondName,date,password,password2)

    }

    fun checkAllValidation(name: String, secondName: String, date: String, password: String, password2: String){
        checkValidationName(name)
        checkValidationSecondName(secondName)
        checkValidationDateOfBirthday(date)
        checkValidationPassword(password)
        checkMatchingPassword(password, password2)
    }


    fun checkValidation(): Boolean{
        return (correctNameMut.value == true
                && correctSecondNameMut.value == true
                && correctDateOfBirthdayMut.value == true
                && correctPasswordMut.value == true
                && matchingPasswordMut.value == true)
    }

    fun saveUserNameToStorage(name: String){
        putInSharedPreference.putInSharedPref(Constants.USER_NAME, name)
    }

    fun clearUserLiveData(){
        userRegistrationMut.value = null
    }
}