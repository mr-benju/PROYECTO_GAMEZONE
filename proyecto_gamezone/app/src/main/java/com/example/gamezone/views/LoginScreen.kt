package com.example.gamezone.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gamezone.helper.Alert
import com.example.gamezone.viewModel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = contrasena, onValueChange = { contrasena = it }, label = { Text("Contraseña") })
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            val result = viewModel.login(correo, contrasena)
            if (result == "ok") {
                onLoginSuccess()
            } else {
                Alert.show(context, result)
            }
        }) {
            Text("Iniciar Sesión")
        }
    }
}
