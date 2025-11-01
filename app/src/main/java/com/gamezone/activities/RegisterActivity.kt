package com.gamezone.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gamezone.R
import com.gamezone.data.RegistrationResult
import com.gamezone.models.GameGenre
import com.gamezone.utils.ValidationResult
import com.gamezone.viewmodels.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    
    private val viewModel = RegisterViewModel()
    
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
            viewModel.fullName = it
            validateField(fullNameInput, viewModel.validateFullName())
        })
        
        emailInput.addTextChangedListener(createTextWatcher { 
            viewModel.email = it
            validateField(emailInput, viewModel.validateEmail())
        })
        
        passwordInput.addTextChangedListener(createTextWatcher { 
            viewModel.password = it
            validateField(passwordInput, viewModel.validatePassword())
        })
        
        confirmPasswordInput.addTextChangedListener(createTextWatcher { 
            viewModel.confirmPassword = it
            validateField(confirmPasswordInput, viewModel.validateConfirmPassword())
        })
        
        phoneInput.addTextChangedListener(createTextWatcher { 
            viewModel.phone = it
            validateField(phoneInput, viewModel.validatePhone())
        })
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
    
    private fun validateField(editText: EditText, result: ValidationResult) {
        when (result) {
            is ValidationResult.Success -> editText.error = null
            is ValidationResult.Error -> editText.error = result.message
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
                        viewModel.selectedGenres.add(genre)
                    } else {
                        viewModel.selectedGenres.remove(genre)
                    }
                }
            }
            genreCheckBoxes[genre] = checkBox
            genresContainer.addView(checkBox)
        }
    }
    
    private fun setupRegisterButton() {
        registerButton.setOnClickListener {
            if (viewModel.isFormValid()) {
                when (val result = viewModel.registerUser()) {
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
            } else {
                val validations = viewModel.validateAllFields()
                validations.forEach { (field, result) ->
                    if (result is ValidationResult.Error) {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
