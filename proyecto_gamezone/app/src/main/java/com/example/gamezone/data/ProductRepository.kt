package com.example.gamezone.data

import com.example.gamezone.models.Product

object ProductRepository {
    val products = listOf(
        Product(
            1, "The Legend of Zelda: Tears of the Kingdom", "Nintendo Switch", 59990.0,
            "https://cdn.example.com/zelda.jpg",
            "Explora el vasto mundo de Hyrule con nuevas habilidades y aventuras."
        ),
        Product(
            2, "God of War: Ragnarök", "PlayStation 5", 69990.0,
            "https://cdn.example.com/gow.jpg",
            "Kratos y Atreus enfrentan su destino en el fin del mundo nórdico."
        ),
        Product(
            3, "Cyberpunk 2077", "PC", 39990.0,
            "https://cdn.example.com/cyberpunk.jpg",
            "Vive en Night City, una megalópolis obsesionada con el poder y la tecnología."
        )
    )
}