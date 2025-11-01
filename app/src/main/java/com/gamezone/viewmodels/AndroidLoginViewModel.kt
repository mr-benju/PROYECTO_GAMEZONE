package com.gamezone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.data.LoginResult

class AndroidLoginViewModel : ViewModel() {
    
    private val delegate = LoginViewModel()
    
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    
    fun updateEmail(email: String) {
        delegate.email = email
        val result = delegate.validateEmail()
        _emailError.value = result.getErrorMessage()
    }
    
    fun updatePassword(password: String) {
        delegate.password = password
        val result = delegate.validatePassword()
        _passwordError.value = result.getErrorMessage()
    }
    
    fun attemptLogin() {
        val result = delegate.loginUser()
        _loginResult.value = result
    }
}
