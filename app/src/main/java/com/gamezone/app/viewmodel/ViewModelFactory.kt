package com.gamezone.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gamezone.app.data.CartRepository
import com.gamezone.app.data.GamesRepository
import com.gamezone.app.data.UserRepository

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val gamesRepository: GamesRepository? = null,
    private val cartRepository: CartRepository? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(CatalogViewModel::class.java) -> {
                CatalogViewModel(
                    gamesRepository ?: throw IllegalArgumentException("GamesRepository is required for CatalogViewModel"),
                    cartRepository ?: throw IllegalArgumentException("CartRepository is required for CatalogViewModel")
                ) as T
            }
            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(
                    cartRepository ?: throw IllegalArgumentException("CartRepository is required for CartViewModel")
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}