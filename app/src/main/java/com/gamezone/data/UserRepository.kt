package com.gamezone.data

import com.gamezone.models.User

object UserRepository {
    private val users = mutableListOf<User>()
    
    fun registerUser(user: User): RegistrationResult {
        if (users.any { it.email == user.email }) {
            return RegistrationResult.Error("El correo electrónico ya está registrado")
        }
        
        users.add(user)
        return RegistrationResult.Success(user)
    }
    
    fun loginUser(email: String, password: String): LoginResult {
        val user = users.find { it.email == email }
        
        return when {
            user == null -> LoginResult.Error("Usuario no encontrado")
            user.password != password -> LoginResult.Error("Contraseña incorrecta")
            else -> LoginResult.Success(user)
        }
    }
    
    fun getAllUsers(): List<User> = users.toList()
    
    fun clearAll() {
        users.clear()
    }
}

sealed class RegistrationResult {
    data class Success(val user: User) : RegistrationResult()
    data class Error(val message: String) : RegistrationResult()
}

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
