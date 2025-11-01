package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.R
import com.gamezone.app.data.User
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityRegisterBinding
import com.gamezone.app.utils.ValidationUtils
import java.util.UUID

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userRepository: UserRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        userRepository = UserRepository.getInstance(this)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.registerButton.setOnClickListener {
            attemptRegister()
        }
        
        binding.loginLink.setOnClickListener {
            finish()
        }
    }
    
    private fun attemptRegister() {
        clearErrors()
        
        val name = binding.nameEditText.text.toString().trim()
        val username = binding.usernameEditText.text.toString().trim()
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()
        
        if (!validateInput(name, username, email, password, confirmPassword)) {
            return
        }
        
        val user = User(
            id = UUID.randomUUID().toString(),
            fullName = name,
            username = username,
            email = email,
            password = password
        )
        
        val success = userRepository.registerUser(user)
        
        if (success) {
            Toast.makeText(this, getString(R.string.success_register), Toast.LENGTH_LONG).show()
            finish()
        } else {
            binding.emailLayout.error = "Este correo o usuario ya está registrado"
            binding.usernameLayout.error = " "
        }
    }
    
    private fun validateInput(
        name: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        var isValid = true
        
        if (!ValidationUtils.isValidName(name)) {
            binding.nameLayout.error = getString(R.string.error_empty_name)
            isValid = false
        }
        
        if (!ValidationUtils.isValidUsername(username)) {
            binding.usernameLayout.error = getString(R.string.error_empty_username)
            isValid = false
        }
        
        if (email.isEmpty()) {
            binding.emailLayout.error = getString(R.string.error_empty_email)
            isValid = false
        } else if (!ValidationUtils.isValidEmail(email)) {
            binding.emailLayout.error = getString(R.string.error_invalid_email)
            isValid = false
        }
        
        if (password.isEmpty()) {
            binding.passwordLayout.error = getString(R.string.error_empty_password)
            isValid = false
        } else if (!ValidationUtils.isValidPassword(password)) {
            binding.passwordLayout.error = getString(R.string.error_short_password)
            isValid = false
        }
        
        if (!ValidationUtils.passwordsMatch(password, confirmPassword)) {
            binding.confirmPasswordLayout.error = getString(R.string.error_password_mismatch)
            isValid = false
        }
        
        return isValid
    }
    
    private fun clearErrors() {
        binding.nameLayout.error = null
        binding.usernameLayout.error = null
        binding.emailLayout.error = null
        binding.passwordLayout.error = null
        binding.confirmPasswordLayout.error = null
    }
}
