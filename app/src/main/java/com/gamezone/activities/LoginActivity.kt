package com.gamezone.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.R
import com.gamezone.data.LoginResult
import com.gamezone.viewmodels.AndroidLoginViewModel

class LoginActivity : AppCompatActivity() {
    
    private val viewModel: AndroidLoginViewModel by viewModels()
    
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        initializeViews()
        setupValidation()
        setupLoginButton()
        setupRegisterLink()
    }
    
    private fun initializeViews() {
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        registerLink = findViewById(R.id.registerLink)
    }
    
    private fun setupValidation() {
        emailInput.addTextChangedListener(createTextWatcher { 
            viewModel.updateEmail(it)
        })
        
        passwordInput.addTextChangedListener(createTextWatcher { 
            viewModel.updatePassword(it)
        })
        
        observeValidationErrors()
    }
    
    private fun createTextWatcher(onTextChanged: (String) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                onTextChanged(s?.toString() ?: "")
            }
        }
    }
    
    private fun observeValidationErrors() {
        viewModel.emailError.observe(this) { error ->
            emailInput.error = error
        }
        
        viewModel.passwordError.observe(this) { error ->
            passwordInput.error = error
        }
        
        viewModel.loginResult.observe(this) { result ->
            handleLoginResult(result)
        }
    }
    
    private fun handleLoginResult(result: LoginResult) {
        when (result) {
            is LoginResult.Success -> {
                Toast.makeText(
                    this,
                    "¡Bienvenido ${result.user.fullName}!",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_NAME", result.user.fullName)
                startActivity(intent)
                finish()
            }
            is LoginResult.Error -> {
                val message = when {
                    result.message.contains("no encontrado", ignoreCase = true) ->
                        "Usuario no encontrado. Verifica tu correo electrónico."
                    result.message.contains("incorrecta", ignoreCase = true) ->
                        "Contraseña incorrecta. Por favor intenta nuevamente."
                    else -> result.message
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            viewModel.attemptLogin()
        }
    }
    
    private fun setupRegisterLink() {
        registerLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
