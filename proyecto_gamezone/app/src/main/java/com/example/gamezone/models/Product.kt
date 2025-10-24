package com.example.gamezone.models

data class Product(
    val id: Int,
    val name: String,
    val platform: String,
    val price: Double,
    val imageUrl: String,
    val description: String
)