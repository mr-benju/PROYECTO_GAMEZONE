package com.gamezone.app.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartRepository private constructor(context: Context) {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences("GameZoneCart", Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        @Volatile
        private var instance: CartRepository? = null
        
        fun getInstance(context: Context): CartRepository {
            return instance ?: synchronized(this) {
                instance ?: CartRepository(context.applicationContext).also { instance = it }
            }
        }
    }
    
    fun addToCart(game: Game, quantity: Int = 1): Boolean {
        val items = getCartItems().toMutableList()
        
        val existingItem = items.find { it.game.id == game.id }
        
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(game, quantity))
        }
        
        saveCartItems(items)
        return true
    }
    
    fun removeFromCart(gameId: String): Boolean {
        val items = getCartItems().toMutableList()
        val removed = items.removeIf { it.game.id == gameId }
        
        if (removed) {
            saveCartItems(items)
        }
        
        return removed
    }
    
    fun updateQuantity(gameId: String, quantity: Int): Boolean {
        if (quantity <= 0) {
            return removeFromCart(gameId)
        }
        
        val items = getCartItems().toMutableList()
        val item = items.find { it.game.id == gameId }
        
        if (item != null) {
            item.quantity = quantity
            saveCartItems(items)
            return true
        }
        
        return false
    }
    
    fun getCartItems(): List<CartItem> {
        val json = sharedPreferences.getString("cart_items", null) ?: return emptyList()
        val type = object : TypeToken<List<CartItem>>() {}.type
        return gson.fromJson(json, type)
    }
    
    fun getCartItemCount(): Int {
        return getCartItems().sumOf { it.quantity }
    }
    
    fun getCartTotal(): Double {
        return getCartItems().sumOf { it.subtotal }
    }
    
    fun clearCart() {
        sharedPreferences.edit().remove("cart_items").apply()
    }
    
    private fun saveCartItems(items: List<CartItem>) {
        val json = gson.toJson(items)
        sharedPreferences.edit().putString("cart_items", json).apply()
    }
}
