package com.gamezone.app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.R
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityRegisterBinding
import com.gamezone.app.viewmodel.RegisterViewModel
import com.gamezone.app.viewmodel.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory(UserRepository.getInstance(this))
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupObservers() {
        viewModel.registerResult.observe(this) { result ->
            when (result) {
                is RegisterViewModel.RegisterResult.Success -> {
                    Toast.makeText(this, getString(R.string.success_register), Toast.LENGTH_LONG).show()
                    finish()
                }
                is RegisterViewModel.RegisterResult.Error -> {
                }
            }
        }
        
        viewModel.nameError.observe(this) { error ->
            binding.nameLayout.error = error
        }
        
        viewModel.usernameError.observe(this) { error ->
            binding.usernameLayout.error = error
        }
        
        viewModel.emailError.observe(this) { error ->
            binding.emailLayout.error = error
        }
        
        viewModel.passwordError.observe(this) { error ->
            binding.passwordLayout.error = error
        }
        
        viewModel.confirmPasswordError.observe(this) { error ->
            binding.confirmPasswordLayout.error = error
        }
    }
    
    private fun setupClickListeners() {
        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val username = binding.usernameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()
            
            viewModel.register(name, username, email, password, confirmPassword)
        }
        
        binding.loginLink.setOnClickListener {
            finish()
        }
    }
}
