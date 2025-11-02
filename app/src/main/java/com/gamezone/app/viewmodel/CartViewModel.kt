package com.gamezone.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.app.data.CartItem
import com.gamezone.app.data.CartRepository

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {
    
    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems
    
    private val _cartTotal = MutableLiveData<Double>()
    val cartTotal: LiveData<Double> = _cartTotal
    
    private val _cartEmpty = MutableLiveData<Boolean>()
    val cartEmpty: LiveData<Boolean> = _cartEmpty
    
    private val _checkoutResult = MutableLiveData<CheckoutResult>()
    val checkoutResult: LiveData<CheckoutResult> = _checkoutResult
    
    init {
        loadCart()
    }
    
    fun loadCart() {
        val items = cartRepository.getCartItems()
        _cartItems.value = items
        _cartTotal.value = cartRepository.getCartTotal()
        _cartEmpty.value = items.isEmpty()
    }
    
    fun removeItem(gameId: String) {
        cartRepository.removeFromCart(gameId)
        loadCart()
    }
    
    fun updateQuantity(gameId: String, quantity: Int) {
        cartRepository.updateQuantity(gameId, quantity)
        loadCart()
    }
    
    fun clearCart() {
        cartRepository.clearCart()
        loadCart()
    }
    
    fun checkout() {
        val items = cartRepository.getCartItems()
        if (items.isEmpty()) {
            _checkoutResult.value = CheckoutResult.Error("El carrito está vacío")
            return
        }
        
        val total = cartRepository.getCartTotal()
        cartRepository.clearCart()
        loadCart()
        _checkoutResult.value = CheckoutResult.Success(total)
    }
    
    sealed class CheckoutResult {
        data class Success(val total: Double) : CheckoutResult()
        data class Error(val message: String) : CheckoutResult()
    }
}
