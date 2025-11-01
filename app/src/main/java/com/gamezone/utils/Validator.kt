package com.gamezone.utils

object Validator {
    
    fun validateFullName(name: String): ValidationResult {
        return when {
            name.isBlank() -> ValidationResult.Error("El nombre no puede estar vacío")
            name.length > 100 -> ValidationResult.Error("El nombre debe tener máximo 100 caracteres")
            !name.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) -> 
                ValidationResult.Error("El nombre solo puede contener letras y espacios")
            else -> ValidationResult.Success
        }
    }
    
    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.Error("El correo no puede estar vacío")
            email.length > 60 -> ValidationResult.Error("El correo debe tener máximo 60 caracteres")
            !email.endsWith("@duoc.cl") -> 
                ValidationResult.Error("El correo debe ser de dominio @duoc.cl")
            !isValidEmailFormat(email) -> 
                ValidationResult.Error("Formato de correo inválido")
            else -> ValidationResult.Success
        }
    }
    
    private fun isValidEmailFormat(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"
        return email.matches(Regex(emailPattern))
    }
    
    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult.Error("La contraseña no puede estar vacía")
            password.length < 10 -> 
                ValidationResult.Error("La contraseña debe tener al menos 10 caracteres")
            !password.any { it.isUpperCase() } -> 
                ValidationResult.Error("Debe incluir al menos una letra mayúscula")
            !password.any { it.isLowerCase() } -> 
                ValidationResult.Error("Debe incluir al menos una letra minúscula")
            !password.any { it.isDigit() } -> 
                ValidationResult.Error("Debe incluir al menos un número")
            !password.any { it in "@#$%&*!?_-" } -> 
                ValidationResult.Error("Debe incluir al menos un carácter especial (@#$%&*!?_-)")
            else -> ValidationResult.Success
        }
    }
    
    fun validatePasswordConfirmation(password: String, confirmation: String): ValidationResult {
        return if (password == confirmation) {
            ValidationResult.Success
        } else {
            ValidationResult.Error("Las contraseñas no coinciden")
        }
    }
    
    fun validatePhone(phone: String): ValidationResult {
        if (phone.isBlank()) return ValidationResult.Success
        
        return when {
            !phone.matches(Regex("^[0-9]{8,15}$")) -> 
                ValidationResult.Error("El teléfono debe tener entre 8 y 15 dígitos")
            else -> ValidationResult.Success
        }
    }
    
    fun validateGenreSelection(genres: List<String>): ValidationResult {
        return if (genres.isEmpty()) {
            ValidationResult.Error("Debe seleccionar al menos un género favorito")
        } else {
            ValidationResult.Success
        }
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
    
    fun isSuccess(): Boolean = this is Success
    fun getErrorMessage(): String? = if (this is Error) message else null
}
