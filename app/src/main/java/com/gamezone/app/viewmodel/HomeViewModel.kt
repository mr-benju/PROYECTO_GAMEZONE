package com.gamezone.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.app.data.User
import com.gamezone.app.data.UserRepository

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    
    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser
    
    private val _logoutEvent = MutableLiveData<Boolean>()
    val logoutEvent: LiveData<Boolean> = _logoutEvent
    
    init {
        loadCurrentUser()
    }
    
    private fun loadCurrentUser() {
        _currentUser.value = userRepository.getCurrentUser()
    }
    
    fun logout() {
        userRepository.logout()
        _logoutEvent.value = true
    }
}
