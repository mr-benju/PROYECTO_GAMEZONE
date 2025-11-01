package com.example.gamezone.views

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.gamezone.R

@Composable
fun InicioScreen(onLoginClick: () -> Unit,
                 onRegisterClick: () -> Unit) {

    // 1) Animación infinito arriba/abajo
    val infiniteTransition = rememberInfiniteTransition()
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -20f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = FastOutSlowInEasing)
        )
    )

    // 2) color del fondo - toggle blanco / negro
    var fondoOscuro by remember { mutableStateOf(false) }
    val fondo = if (fondoOscuro) Color.Black else Color.White
    val textoColor = if (fondoOscuro) Color.White else Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .clickable {
                // cada toque cambia dark/light
                fondoOscuro = !fondoOscuro
            }
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Logo animado ↓
        Image(
            painter = painterResource(id = R.drawable.gamezone_logo),
            contentDescription = "Logo GameZone",
            modifier = Modifier
                .size(200.dp)
                .offset(y = offsetY.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ir al Login", color = textoColor)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onRegisterClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ir al Registro", color = textoColor)
        }
    }
}
