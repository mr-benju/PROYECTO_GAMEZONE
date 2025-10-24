package com.example.gamezone.navigation

import androidx.compose.runtime.*
import com.example.gamezone.viewModel.LoginViewModel
import com.example.gamezone.viewModel.ProductViewModel
import com.example.gamezone.viewModel.RegisterViewModel
import com.example.gamezone.views.*

@Composable
fun AppNavigation() {
    // Estado que controla qué pantalla se está mostrando
    var currentScreen by remember { mutableStateOf("inicio") }

    // ViewModels compartidos
    val loginVM = remember { LoginViewModel() }
    val registerVM = remember { RegisterViewModel() }
    val productVM = remember { ProductViewModel() }

    when (currentScreen) {

        // Pantalla inicial
        "inicio" -> InicioScreen(
            onLoginClick = { currentScreen = "login" },
            onRegisterClick = { currentScreen = "registro" }
        )

        // Pantalla de login
        "login" -> LoginScreen(
            viewModel = loginVM,
            onLoginSuccess = { currentScreen = "home" }, // pasa a Home
            onBack = { currentScreen = "inicio" }
        )

        // Pantalla de registro
        "registro" -> RegisterScreen(
            viewModel = registerVM,
            onBack = { currentScreen = "inicio" }
        )

        // Pantalla principal (tienda)
        "home" -> HomeScreen(
            productVM = productVM,
            onCartClick = { currentScreen = "cart" },
            onLogout = { currentScreen = "inicio" }
        )

        // Carrito de compras
        "cart" -> CartScreen(
            productVM = productVM,
            onBack = { currentScreen = "home" }
        )
    }
}
