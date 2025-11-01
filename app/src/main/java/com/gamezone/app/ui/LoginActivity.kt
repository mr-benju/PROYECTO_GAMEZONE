package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityLoginBinding
import com.gamezone.app.viewmodel.LoginViewModel
import com.gamezone.app.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserRepository.getInstance(this))
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        viewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginViewModel.LoginResult.Success -> {
                    Toast.makeText(this, "Bienvenido ${result.user.fullName}!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                is LoginViewModel.LoginResult.Error -> {
                }
            }
        }
        
        viewModel.emailError.observe(this) { error ->
            binding.emailLayout.error = error
        }
        
        viewModel.passwordError.observe(this) { error ->
            binding.passwordLayout.error = error
        }
    }
    
    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(email, password)
        }
        
        binding.registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
