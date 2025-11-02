package com.gamezone.app.data

data class Game(
    val id: String,
    val title: String,
    val description: String,
    val platform: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val rating: Double,
    val inStock: Boolean
)

data class GamesCatalog(
    val games: List<Game>
)
