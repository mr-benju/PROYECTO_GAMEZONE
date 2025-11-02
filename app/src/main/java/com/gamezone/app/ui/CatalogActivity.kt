package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamezone.app.data.CartRepository
import com.gamezone.app.data.GamesRepository
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityCatalogBinding
import com.gamezone.app.viewmodel.CatalogViewModel
import com.gamezone.app.viewmodel.ViewModelFactory
import com.gamezone.app.ui.adapters.GamesAdapter
import com.google.android.material.chip.Chip

class CatalogActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels {
        ViewModelFactory(
            UserRepository.getInstance(this),
            GamesRepository.getInstance(this),
            CartRepository.getInstance(this)
        )
    }
    private lateinit var gamesAdapter: GamesAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupSearch()
        setupObservers()
        setupClickListeners()
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.updateCartCount()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        gamesAdapter = GamesAdapter { game ->
            viewModel.addToCart(game)
        }
        
        binding.gamesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            adapter = gamesAdapter
        }
    }
    
    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchGames(s?.toString() ?: "")
            }
        })
    }
    
    private fun setupObservers() {
        viewModel.games.observe(this) { games ->
            gamesAdapter.submitList(games)
            binding.emptyText.visibility = if (games.isEmpty()) View.VISIBLE else View.GONE
        }
        
        viewModel.categories.observe(this) { categories ->
            setupCategoryChips(categories)
        }
        
        viewModel.cartItemCount.observe(this) { count ->
            binding.cartFab.text = if (count > 0) {
                "Ver Carrito ($count)"
            } else {
                "Ver Carrito"
            }
        }
        
        viewModel.addToCartResult.observe(this) { result ->
            when (result) {
                is CatalogViewModel.AddToCartResult.Success -> {
                    Toast.makeText(
                        this,
                        "${result.gameTitle} agregado al carrito",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CatalogViewModel.AddToCartResult.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun setupCategoryChips(categories: List<String>) {
        binding.categoryChipGroup.removeAllViews()
        
        val allChip = Chip(this).apply {
            text = "Todos"
            isCheckable = true
            isChecked = true
            setOnClickListener {
                viewModel.filterByCategory(null)
            }
        }
        binding.categoryChipGroup.addView(allChip)
        
        categories.forEach { category ->
            val chip = Chip(this).apply {
                text = category
                isCheckable = true
                setOnClickListener {
                    viewModel.filterByCategory(category)
                }
            }
            binding.categoryChipGroup.addView(chip)
        }
    }
    
    private fun setupClickListeners() {
        binding.cartFab.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
