package com.gamezone.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.app.data.UserRepository
import com.gamezone.app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userRepository: UserRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        userRepository = UserRepository.getInstance(this)
        
        setupUserInterface()
        setupClickListeners()
    }
    
    private fun setupUserInterface() {
        val user = userRepository.getCurrentUser()
        if (user != null) {
            binding.userNameText.text = "¡Hola, ${user.fullName}!"
        }
    }
    
    private fun setupClickListeners() {
        binding.logoutButton.setOnClickListener {
            userRepository.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
