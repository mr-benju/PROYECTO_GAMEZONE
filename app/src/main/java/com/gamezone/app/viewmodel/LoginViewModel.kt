package com.gamezone.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.app.data.User
import com.gamezone.app.data.UserRepository
import com.gamezone.app.utils.ValidationUtils

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    
    fun login(emailOrUsername: String, password: String) {
        clearErrors()
        
        if (!validateInput(emailOrUsername, password)) {
            return
        }
        
        val user = userRepository.loginUser(emailOrUsername, password)
        
        if (user != null) {
            userRepository.saveCurrentUser(user)
            _loginResult.value = LoginResult.Success(user)
        } else {
            _emailError.value = "Usuario o contraseña incorrectos"
            _passwordError.value = " "
            _loginResult.value = LoginResult.Error("Credenciales inválidas")
        }
    }
    
    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true
        
        if (email.isEmpty()) {
            _emailError.value = "El correo electrónico es requerido"
            isValid = false
        }
        
        if (password.isEmpty()) {
            _passwordError.value = "La contraseña es requerida"
            isValid = false
        }
        
        return isValid
    }
    
    private fun clearErrors() {
        _emailError.value = null
        _passwordError.value = null
    }
    
    sealed class LoginResult {
        data class Success(val user: User) : LoginResult()
        data class Error(val message: String) : LoginResult()
    }
}
