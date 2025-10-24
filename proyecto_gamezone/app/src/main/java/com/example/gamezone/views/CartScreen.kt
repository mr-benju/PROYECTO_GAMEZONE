package com.example.gamezone.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamezone.viewModel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    productVM: ProductViewModel,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi carrito") },
                navigationIcon = {
                    TextButton(onClick = { onBack() }) {
                        Text("⬅ Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            if (productVM.cart.isEmpty()) {
                Text("Tu carrito está vacío.")
            } else {
                productVM.cart.forEach { product ->
                    Text("${product.name} - $${product.price}")
                }
                Divider(Modifier.padding(vertical = 8.dp))
                Text("Total: $${productVM.getTotal()}")

                Button(
                    onClick = { productVM.clearCart() },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Confirmar compra")
                }
            }
        }
    }
}
