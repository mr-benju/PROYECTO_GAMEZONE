package com.gamezone.models

data class User(
    val fullName: String,
    val email: String,
    val password: String,
    val phone: String? = null,
    val favoriteGenres: List<GameGenre>
) {
    override fun toString(): String {
        return """
            |Usuario:
            |  Nombre: $fullName
            |  Email: $email
            |  Teléfono: ${phone ?: "No proporcionado"}
            |  Géneros favoritos: ${favoriteGenres.joinToString(", ") { it.displayName }}
        """.trimMargin()
    }
}
