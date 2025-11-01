package com.example.gamezone.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamezone.R

@Composable
fun InicioScreen(onLoginClick: () -> Unit,
                 onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.gamezone_logo),
            contentDescription = "Logo GameZone",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ir al Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRegisterClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ir al Registro")
        }
    }
}

// Visualizar, con parametros vacios
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInicioScreen() {
    InicioScreen(
        onLoginClick = {},
        onRegisterClick = {}
    )
}

