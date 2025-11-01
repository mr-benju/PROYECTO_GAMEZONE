package com.gamezone.app.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserRepository private constructor(context: Context) {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("GameZonePrefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        
        fun getInstance(context: Context): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository(context.applicationContext).also { instance = it }
            }
        }
    }
    
    fun registerUser(user: User): Boolean {
        val users = getAllUsers().toMutableList()
        
        if (users.any { it.email == user.email || it.username == user.username }) {
            return false
        }
        
        users.add(user)
        saveAllUsers(users)
        return true
    }
    
    fun loginUser(emailOrUsername: String, password: String): User? {
        val users = getAllUsers()
        return users.find { 
            (it.email == emailOrUsername || it.username == emailOrUsername) && 
            it.password == password 
        }
    }
    
    fun saveCurrentUser(user: User) {
        val json = gson.toJson(user)
        sharedPreferences.edit().putString("current_user", json).apply()
    }
    
    fun getCurrentUser(): User? {
        val json = sharedPreferences.getString("current_user", null) ?: return null
        return gson.fromJson(json, User::class.java)
    }
    
    fun logout() {
        sharedPreferences.edit().remove("current_user").apply()
    }
    
    fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }
    
    private fun getAllUsers(): List<User> {
        val json = sharedPreferences.getString("all_users", null) ?: return emptyList()
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, type)
    }
    
    private fun saveAllUsers(users: List<User>) {
        val json = gson.toJson(users)
        sharedPreferences.edit().putString("all_users", json).apply()
    }
}
