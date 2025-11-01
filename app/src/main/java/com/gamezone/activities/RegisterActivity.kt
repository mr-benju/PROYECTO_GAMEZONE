package com.gamezone.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.R
import com.gamezone.data.RegistrationResult
import com.gamezone.models.GameGenre
import com.gamezone.viewmodels.AndroidRegisterViewModel

class RegisterActivity : AppCompatActivity() {
    
    private val viewModel: AndroidRegisterViewModel by viewModels()
    
    private lateinit var fullNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var registerButton: Button
    private lateinit var genresContainer: LinearLayout
    
    private val genreCheckBoxes = mutableMapOf<GameGenre, CheckBox>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        
        initializeViews()
        setupValidation()
        setupGenreCheckboxes()
        setupRegisterButton()
    }
    
    private fun initializeViews() {
        fullNameInput = findViewById(R.id.fullNameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput)
        phoneInput = findViewById(R.id.phoneInput)
        registerButton = findViewById(R.id.registerButton)
        genresContainer = findViewById(R.id.genresContainer)
    }
    
    private fun setupValidation() {
        fullNameInput.addTextChangedListener(createTextWatcher { 
            viewModel.updateFullName(it)
        })
        
        emailInput.addTextChangedListener(createTextWatcher { 
            viewModel.updateEmail(it)
        })
        
        passwordInput.addTextChangedListener(createTextWatcher { 
            viewModel.updatePassword(it)
        })
        
        confirmPasswordInput.addTextChangedListener(createTextWatcher { 
            viewModel.updateConfirmPassword(it)
        })
        
        phoneInput.addTextChangedListener(createTextWatcher { 
            viewModel.updatePhone(it)
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
        viewModel.fullNameError.observe(this) { error ->
            fullNameInput.error = error
        }
        
        viewModel.emailError.observe(this) { error ->
            emailInput.error = error
        }
        
        viewModel.passwordError.observe(this) { error ->
            passwordInput.error = error
        }
        
        viewModel.confirmPasswordError.observe(this) { error ->
            confirmPasswordInput.error = error
        }
        
        viewModel.phoneError.observe(this) { error ->
            phoneInput.error = error
        }
        
        viewModel.registrationResult.observe(this) { result ->
            handleRegistrationResult(result)
        }
    }
    
    private fun handleRegistrationResult(result: RegistrationResult) {
        when (result) {
            is RegistrationResult.Success -> {
                Toast.makeText(
                    this,
                    "¡Registro exitoso! Bienvenido ${result.user.fullName}",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
            is RegistrationResult.Error -> {
                Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun setupGenreCheckboxes() {
        GameGenre.getAllGenres().forEach { genre ->
            val checkBox = CheckBox(this).apply {
                text = genre.displayName
                textSize = 16f
                setPadding(16, 16, 16, 16)
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        viewModel.addGenre(genre)
                    } else {
                        viewModel.removeGenre(genre)
                    }
                }
            }
            genreCheckBoxes[genre] = checkBox
            genresContainer.addView(checkBox)
        }
    }
    
    private fun setupRegisterButton() {
        registerButton.setOnClickListener {
            viewModel.attemptRegistration()
        }
    }
}
