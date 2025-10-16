package com.example.gamezone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gamezone.navigation.AppNavigation
import com.example.gamezone.ui.theme.GamezoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicamos el tema de la app
            GamezoneTheme {
                // Cargamos la navegación principal
                AppNavigation()
            }
        }
    }
}
