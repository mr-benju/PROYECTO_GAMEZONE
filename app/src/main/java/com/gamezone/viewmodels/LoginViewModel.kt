package com.gamezone.viewmodels

import com.gamezone.data.LoginResult
import com.gamezone.data.UserRepository
import com.gamezone.utils.Validator
import com.gamezone.utils.ValidationResult

class LoginViewModel {
    
    var email: String = ""
    var password: String = ""
    
    fun validateEmail(): ValidationResult {
        val result = Validator.validateEmail(email)
        return if (result.isSuccess()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Por favor ingrese un correo válido")
        }
    }
    
    fun validatePassword(): ValidationResult {
        return if (password.isBlank()) {
            ValidationResult.Error("Por favor ingrese su contraseña")
        } else {
            ValidationResult.Success
        }
    }
    
    fun isFormValid(): Boolean {
        return validateEmail().isSuccess() && validatePassword().isSuccess()
    }
    
    fun loginUser(): LoginResult {
        if (!isFormValid()) {
            return LoginResult.Error("Por favor complete todos los campos")
        }
        
        return UserRepository.loginUser(email.trim().lowercase(), password)
    }
}
