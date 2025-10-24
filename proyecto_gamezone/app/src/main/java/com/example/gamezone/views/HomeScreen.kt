package com.example.gamezone.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gamezone.viewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    productVM: ProductViewModel,
    onCartClick: () -> Unit,
    onLogout: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GameZone") },
                actions = {
                    // Botón carrito
                    TextButton(onClick = { onCartClick() }) {
                        Text("🛒 ${productVM.cart.size}")
                    }
                    // Botón cerrar sesión
                    TextButton(onClick = { onLogout() }) {
                        Text("Salir")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize()
        ) {
            items(productVM.products) { product ->
                ProductCard(
                    product = product,
                    onAddToCart = { productVM.addToCart(it) }
                )
            }
        }
    }
}
