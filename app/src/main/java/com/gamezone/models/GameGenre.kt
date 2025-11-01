package com.gamezone.models

enum class GameGenre(val displayName: String) {
    FICCION("Ficción"),
    NO_FICCION("No Ficción"),
    MISTERIO("Misterio"),
    TERROR("Terror"),
    SUSPENSO("Suspenso"),
    HISTORIA("Historia");
    
    companion object {
        fun getAllGenres(): List<GameGenre> = values().toList()
        
        fun fromDisplayName(name: String): GameGenre? {
            return values().find { it.displayName == name }
        }
    }
}
