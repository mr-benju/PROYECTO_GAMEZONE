package com.example.gamezone.data

import com.example.gamezone.R
import com.example.gamezone.models.Product

object ProductRepository {
    val products = listOf(
        Product(
            1, "The Legend of Zelda: Tears of the Kingdom", "Nintendo Switch", 59990.0,
            R.drawable.zelda,
            "Explora el vasto mundo de Hyrule con nuevas habilidades y aventuras."
        ),
        Product(
            2, "God of War: Ragnarök", "PlayStation 5", 69990.0,
            R.drawable.gow,
            "Kratos y Atreus enfrentan su destino en el fin del mundo nórdico."
        ),
        Product(
            3, "Cyberpunk 2077", "PC", 39990.0,
            R.drawable.cyberpunk,
            "Vive en Night City, una megalópolis obsesionada con el poder y la tecnología."
        ),
        Product(
            4, "The Last of Us Part I", "PlayStation 4", 49990.0,
            R.drawable.tlou1,
            "La historia original remasterizada para PS4."
        ),
        Product(
            5, "The Last of Us Part II", "PlayStation 4", 59990.0,
                R.drawable.tlou2,
            "Continúa el viaje emocional de Ellie en un mundo devastado."
        ),
        Product(
            6, "Call of Duty Black Ops III", "PlayStation 4", 39990.0,
            R.drawable.bo3,
            "Shooter futurista con campaña intensa y multijugador icónico."
        ),
        Product(
            7, "Forza Horizon 5", "Xbox Series X/S", 69990.0,
            R.drawable.forza,
            "Explora México en el festival automovilístico más grande del mundo."
        ),
        Product(
            8, "Super Mario Odyssey", "Nintendo Switch", 52990.0,
            R.drawable.odyssey,
            "Acompaña a Mario en un viaje alrededor del mundo."
        )
    )
}
