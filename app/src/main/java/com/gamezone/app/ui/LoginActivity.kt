package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.R
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityLoginBinding
import com.gamezone.app.utils.ValidationUtils

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userRepository: UserRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        userRepository = UserRepository.getInstance(this)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.loginButton.setOnClickListener {
            attemptLogin()
        }
        
        binding.registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    
    private fun attemptLogin() {
        clearErrors()
        
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()
        
        if (!validateInput(email, password)) {
            return
        }
        
        val user = userRepository.loginUser(email, password)
        
        if (user != null) {
            userRepository.saveCurrentUser(user)
            Toast.makeText(this, "Bienvenido ${user.fullName}!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            binding.emailLayout.error = getString(R.string.error_login_failed)
            binding.passwordLayout.error = " "
        }
    }
    
    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true
        
        if (email.isEmpty()) {
            binding.emailLayout.error = getString(R.string.error_empty_email)
            isValid = false
        }
        
        if (password.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.error_empty_password)
            isValid = false
        }
        
        return isValid
    }
    
    private fun clearErrors() {
        binding.emailLayout.error = null
        binding.passwordLayout.error = null
    }
}
