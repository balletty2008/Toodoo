package com.example.loginui.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.util.Log


class SignUpState {
    var firstName:String by mutableStateOf("")
        private set
    var lastName:String by mutableStateOf("")
        private set
    var emailAddress:String by mutableStateOf("")
        private set
    var password:String by mutableStateOf("")
        private set
    var confirmPassword:String by mutableStateOf("")
        private set
    var checked:Boolean by mutableStateOf(false)
        private set

    val enableButton = firstName.isNotBlank() && lastName.isNotBlank() &&
            confirmPassword.isNotBlank() && password.isNotBlank()

    //fun firstNameChange(newValue:String){
    //    firstName = newValue
    //    Log.d("SignUpState", "firstName=$firstName")
    //}

    fun firstNameChange(newValue:String){
        firstName = newValue
    }

    fun lastNameChange(newValue:String){
        lastName = newValue
    }

    fun emailAddressChange(newValue:String){
        emailAddress = newValue
    }

    fun password(newValue:String){
        password = newValue
    }

    fun confirmPasswordChange(newValue:String){
        confirmPassword = newValue
    }

    fun checkedChange(newValue:Boolean){
        checked = newValue
    }

}