package com.gamezone.app.data

import android.content.Context
import com.google.gson.Gson
import java.io.InputStreamReader

class GamesRepository private constructor(context: Context) {
    
    private val appContext = context.applicationContext
    private val gson = Gson()
    private var cachedGames: List<Game>? = null
    
    companion object {
        @Volatile
        private var instance: GamesRepository? = null
        
        fun getInstance(context: Context): GamesRepository {
            return instance ?: synchronized(this) {
                instance ?: GamesRepository(context).also { instance = it }
            }
        }
    }
    
    fun getAllGames(): List<Game> {
        if (cachedGames != null) {
            return cachedGames!!
        }
        
        return try {
            val inputStream = appContext.resources.openRawResource(
                appContext.resources.getIdentifier("games_catalog", "raw", appContext.packageName)
            )
            val reader = InputStreamReader(inputStream)
            val catalog = gson.fromJson(reader, GamesCatalog::class.java)
            reader.close()
            cachedGames = catalog.games
            catalog.games
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    fun getGameById(id: String): Game? {
        return getAllGames().find { it.id == id }
    }
    
    fun getGamesByCategory(category: String): List<Game> {
        return getAllGames().filter { it.category == category }
    }
    
    fun searchGames(query: String): List<Game> {
        val lowerQuery = query.lowercase()
        return getAllGames().filter { 
            it.title.lowercase().contains(lowerQuery) ||
            it.description.lowercase().contains(lowerQuery) ||
            it.category.lowercase().contains(lowerQuery)
        }
    }
    
    fun getCategories(): List<String> {
        return getAllGames().map { it.category }.distinct().sorted()
    }
}
