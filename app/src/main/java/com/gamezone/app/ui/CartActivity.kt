package com.gamezone.app.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamezone.app.data.CartRepository
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityCartBinding
import com.gamezone.app.viewmodel.CartViewModel
import com.gamezone.app.viewmodel.ViewModelFactory
import com.gamezone.app.ui.adapters.CartAdapter

class CartActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels {
        ViewModelFactory(
            UserRepository.getInstance(this),
            null,
            CartRepository.getInstance(this)
        )
    }
    private lateinit var cartAdapter: CartAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(
            onRemove = { gameId -> viewModel.removeItem(gameId) },
            onQuantityChange = { gameId, quantity -> viewModel.updateQuantity(gameId, quantity) }
        )
        
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }
    }
    
    private fun setupObservers() {
        viewModel.cartItems.observe(this) { items ->
            cartAdapter.submitList(items)
        }
        
        viewModel.cartTotal.observe(this) { total ->
            binding.totalText.text = String.format("$%.2f", total)
        }
        
        viewModel.cartEmpty.observe(this) { isEmpty ->
            binding.emptyCartView.visibility = if (isEmpty) View.VISIBLE else View.GONE
            binding.summaryCard.visibility = if (isEmpty) View.GONE else View.VISIBLE
        }
        
        viewModel.checkoutResult.observe(this) { result ->
            when (result) {
                is CartViewModel.CheckoutResult.Success -> {
                    showCheckoutSuccessDialog(result.total)
                }
                is CartViewModel.CheckoutResult.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.checkoutButton.setOnClickListener {
            viewModel.checkout()
        }
        
        binding.clearCartButton.setOnClickListener {
            showClearCartDialog()
        }
    }
    
    private fun showClearCartDialog() {
        AlertDialog.Builder(this)
            .setTitle("Vaciar Carrito")
            .setMessage("¿Estás seguro de que deseas vaciar el carrito?")
            .setPositiveButton("Sí") { _, _ ->
                viewModel.clearCart()
                Toast.makeText(this, "Carrito vaciado", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    private fun showCheckoutSuccessDialog(total: Double) {
        AlertDialog.Builder(this)
            .setTitle("¡Compra Exitosa!")
            .setMessage(String.format("Tu compra de $%.2f ha sido procesada con éxito.\n\n¡Gracias por tu compra!", total))
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}
