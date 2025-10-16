package com.example.gamezone.navigation


import androidx.compose.runtime.*
import com.example.gamezone.viewModel.LoginViewModel
import com.example.gamezone.viewModel.RegisterViewModel
import com.example.gamezone.views.*

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("inicio") }

    val loginVM = remember { LoginViewModel() }
    val registerVM = remember { RegisterViewModel() }

    when (currentScreen) {
        "inicio" -> InicioScreen(
            onLoginClick = { currentScreen = "login" },
            onRegisterClick = { currentScreen = "registro" }
        )
        "login" -> LoginScreen(
            viewModel = loginVM,
            onLoginSuccess = { currentScreen = "home" }
        )
        "registro" -> RegisterScreen(
            viewModel = registerVM,
            onBack = { currentScreen = "inicio" }
        )
        "home" -> HomeScreen()
    }
}
