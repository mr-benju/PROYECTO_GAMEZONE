package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityHomeBinding
import com.gamezone.app.viewmodel.HomeViewModel
import com.gamezone.app.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(UserRepository.getInstance(this))
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        viewModel.currentUser.observe(this) { user ->
            user?.let {
                binding.userNameText.text = "¡Hola, ${it.fullName}!"
            }
        }
        
        viewModel.logoutEvent.observe(this) { shouldLogout ->
            if (shouldLogout) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }
    }
}
