package com.example.gamezone.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.gamezone.models.Product
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCard(product: Product, onAddToCart: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = product.platform, style = MaterialTheme.typography.bodySmall)

            val priceText = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(product.price)
            Text(text = priceText, style = MaterialTheme.typography.bodyMedium)

            Button(
                onClick = { onAddToCart(product) },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}
