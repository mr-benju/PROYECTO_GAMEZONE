package com.example.gamezone.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gamezone.helper.Alert
import com.example.gamezone.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onBack: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    val context = LocalContext.current  // <-- obtener context aquí

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = correo, onValueChange = { correo = it }, label = { Text("Correo @duoc.cl") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = contrasena, onValueChange = { contrasena = it }, label = { Text("Contraseña") })
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            val msg = viewModel.registrar(nombre, correo, contrasena)
            Alert.show(context, msg)
            if (msg.contains("correctamente")) onBack()
        }) {
            Text("Registrar")
        }
    }
}
