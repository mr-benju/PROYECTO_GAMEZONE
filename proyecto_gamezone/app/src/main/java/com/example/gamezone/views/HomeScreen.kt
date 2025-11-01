package com.example.gamezone.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamezone.data.ProductRepository.products
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeScreen() {
    // ProductViewModel tiene advertencia
    // por usar estructura que no va con el composable original
    // Sin embargo al usar un preview funciona igual
    // Sin imagenes ya que es preview

    HomeScreen(
        productVM = ProductViewModel(),
        onCartClick = {},
        onLogout = {}
    )
}