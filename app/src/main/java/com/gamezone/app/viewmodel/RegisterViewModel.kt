package com.gamezone.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.app.data.User
import com.gamezone.app.data.UserRepository
import com.gamezone.app.utils.ValidationUtils
import java.util.UUID

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    
    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult
    
    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?> = _nameError
    
    private val _usernameError = MutableLiveData<String?>()
    val usernameError: LiveData<String?> = _usernameError
    
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    
    private val _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError
    
    fun register(
        name: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        clearErrors()
        
        if (!validateInput(name, username, email, password, confirmPassword)) {
            return
        }
        
        val user = User(
            id = UUID.randomUUID().toString(),
            fullName = name,
            username = username,
            email = email,
            password = password
        )
        
        val success = userRepository.registerUser(user)
        
        if (success) {
            _registerResult.value = RegisterResult.Success
        } else {
            _emailError.value = "Este correo o usuario ya está registrado"
            _usernameError.value = " "
            _registerResult.value = RegisterResult.Error("Usuario duplicado")
        }
    }
    
    private fun validateInput(
        name: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        var isValid = true
        
        if (!ValidationUtils.isValidName(name)) {
            _nameError.value = "El nombre es requerido"
            isValid = false
        }
        
        if (!ValidationUtils.isValidUsername(username)) {
            _usernameError.value = "El usuario es requerido (mínimo 3 caracteres)"
            isValid = false
        }
        
        if (email.isEmpty()) {
            _emailError.value = "El correo electrónico es requerido"
            isValid = false
        } else if (!ValidationUtils.isValidEmail(email)) {
            _emailError.value = "Correo electrónico no válido"
            isValid = false
        }
        
        if (password.isEmpty()) {
            _passwordError.value = "La contraseña es requerida"
            isValid = false
        } else if (!ValidationUtils.isValidPassword(password)) {
            _passwordError.value = "La contraseña debe tener al menos 6 caracteres"
            isValid = false
        }
        
        if (!ValidationUtils.passwordsMatch(password, confirmPassword)) {
            _confirmPasswordError.value = "Las contraseñas no coinciden"
            isValid = false
        }
        
        return isValid
    }
    
    private fun clearErrors() {
        _nameError.value = null
        _usernameError.value = null
        _emailError.value = null
        _passwordError.value = null
        _confirmPasswordError.value = null
    }
    
    sealed class RegisterResult {
        object Success : RegisterResult()
        data class Error(val message: String) : RegisterResult()
    }
}
