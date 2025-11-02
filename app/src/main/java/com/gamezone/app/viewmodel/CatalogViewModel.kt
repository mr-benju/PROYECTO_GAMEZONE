package com.gamezone.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.app.data.CartRepository
import com.gamezone.app.data.Game
import com.gamezone.app.data.GamesRepository

class CatalogViewModel(
    private val gamesRepository: GamesRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games
    
    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories
    
    private val _selectedCategory = MutableLiveData<String?>()
    val selectedCategory: LiveData<String?> = _selectedCategory
    
    private val _cartItemCount = MutableLiveData<Int>()
    val cartItemCount: LiveData<Int> = _cartItemCount
    
    private val _addToCartResult = MutableLiveData<AddToCartResult>()
    val addToCartResult: LiveData<AddToCartResult> = _addToCartResult
    
    init {
        loadGames()
        loadCategories()
        updateCartCount()
    }
    
    fun loadGames() {
        _games.value = gamesRepository.getAllGames()
    }
    
    fun loadCategories() {
        _categories.value = gamesRepository.getCategories()
    }
    
    fun filterByCategory(category: String?) {
        _selectedCategory.value = category
        _games.value = if (category == null) {
            gamesRepository.getAllGames()
        } else {
            gamesRepository.getGamesByCategory(category)
        }
    }
    
    fun searchGames(query: String) {
        _games.value = if (query.isBlank()) {
            if (_selectedCategory.value != null) {
                gamesRepository.getGamesByCategory(_selectedCategory.value!!)
            } else {
                gamesRepository.getAllGames()
            }
        } else {
            gamesRepository.searchGames(query)
        }
    }
    
    fun addToCart(game: Game) {
        val success = cartRepository.addToCart(game)
        if (success) {
            updateCartCount()
            _addToCartResult.value = AddToCartResult.Success(game.title)
        } else {
            _addToCartResult.value = AddToCartResult.Error("No se pudo agregar al carrito")
        }
    }
    
    fun updateCartCount() {
        _cartItemCount.value = cartRepository.getCartItemCount()
    }
    
    sealed class AddToCartResult {
        data class Success(val gameTitle: String) : AddToCartResult()
        data class Error(val message: String) : AddToCartResult()
    }
}
