package com.example.gamezone.viewModel

import androidx.lifecycle.ViewModel
import com.example.gamezone.data.ProductRepository
import com.example.gamezone.models.Product
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

class ProductViewModel : ViewModel() {
    val products: List<Product> = ProductRepository.products
    private val _cart = mutableStateListOf<Product>()
    val cart: SnapshotStateList<Product> = _cart

    fun addToCart(product: Product) {
        if (!_cart.contains(product)) _cart.add(product)
    }

    fun removeFromCart(product: Product) {
        _cart.remove(product)
    }

    fun clearCart() {
        _cart.clear()
    }

    fun getTotal(): Double = _cart.sumOf { it.price }
}