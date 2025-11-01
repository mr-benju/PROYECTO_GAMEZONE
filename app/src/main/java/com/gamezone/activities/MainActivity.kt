package com.gamezone.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.R

class MainActivity : AppCompatActivity() {
    
    private lateinit var welcomeText: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        welcomeText = findViewById(R.id.welcomeText)
        
        val userName = intent.getStringExtra("USER_NAME") ?: "Usuario"
        welcomeText.text = "¡Bienvenido a GameZone, $userName!\n\nTu tienda de videojuegos favorita"
    }
}
