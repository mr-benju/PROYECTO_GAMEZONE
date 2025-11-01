package com.gamezone.viewmodels

import com.gamezone.data.RegistrationResult
import com.gamezone.data.UserRepository
import com.gamezone.models.GameGenre
import com.gamezone.models.User
import com.gamezone.utils.Validator
import com.gamezone.utils.ValidationResult

class RegisterViewModel {
    
    var fullName: String = ""
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var phone: String = ""
    var selectedGenres: MutableList<GameGenre> = mutableListOf()
    
    fun validateFullName(): ValidationResult {
        return Validator.validateFullName(fullName)
    }
    
    fun validateEmail(): ValidationResult {
        return Validator.validateEmail(email)
    }
    
    fun validatePassword(): ValidationResult {
        return Validator.validatePassword(password)
    }
    
    fun validateConfirmPassword(): ValidationResult {
        return Validator.validatePasswordConfirmation(password, confirmPassword)
    }
    
    fun validatePhone(): ValidationResult {
        return Validator.validatePhone(phone)
    }
    
    fun validateGenres(): ValidationResult {
        return Validator.validateGenreSelection(selectedGenres.map { it.displayName })
    }
    
    fun validateAllFields(): Map<String, ValidationResult> {
        return mapOf(
            "fullName" to validateFullName(),
            "email" to validateEmail(),
            "password" to validatePassword(),
            "confirmPassword" to validateConfirmPassword(),
            "phone" to validatePhone(),
            "genres" to validateGenres()
        )
    }
    
    fun isFormValid(): Boolean {
        return validateAllFields().values.all { it.isSuccess() }
    }
    
    fun registerUser(): RegistrationResult {
        if (!isFormValid()) {
            return RegistrationResult.Error("Por favor, corrija los errores en el formulario")
        }
        
        val user = User(
            fullName = fullName.trim(),
            email = email.trim().lowercase(),
            password = password,
            phone = if (phone.isBlank()) null else phone.trim(),
            favoriteGenres = selectedGenres.toList()
        )
        
        return UserRepository.registerUser(user)
    }
}
