package com.example.gamezone.models

data class Product(
    val id: Int,
    val name: String,
    val platform: String,
    val price: Double,
    val imageRes: Int, // Para cargar los juegos
    val description: String
)