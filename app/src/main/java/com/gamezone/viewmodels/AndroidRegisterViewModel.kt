package com.gamezone.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamezone.data.RegistrationResult
import com.gamezone.models.GameGenre
import com.gamezone.utils.ValidationResult

class AndroidRegisterViewModel : ViewModel() {
    
    private val delegate = RegisterViewModel()
    
    private val _fullNameError = MutableLiveData<String?>()
    val fullNameError: LiveData<String?> = _fullNameError
    
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError
    
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    
    private val _confirmPasswordError = MutableLiveData<String?>()
    val confirmPasswordError: LiveData<String?> = _confirmPasswordError
    
    private val _phoneError = MutableLiveData<String?>()
    val phoneError: LiveData<String?> = _phoneError
    
    private val _genreError = MutableLiveData<String?>()
    val genreError: LiveData<String?> = _genreError
    
    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult
    
    fun updateFullName(name: String) {
        delegate.fullName = name
        val result = delegate.validateFullName()
        _fullNameError.value = result.getErrorMessage()
    }
    
    fun updateEmail(email: String) {
        delegate.email = email
        val result = delegate.validateEmail()
        _emailError.value = result.getErrorMessage()
    }
    
    fun updatePassword(password: String) {
        delegate.password = password
        val result = delegate.validatePassword()
        _passwordError.value = result.getErrorMessage()
    }
    
    fun updateConfirmPassword(confirmPassword: String) {
        delegate.confirmPassword = confirmPassword
        val result = delegate.validateConfirmPassword()
        _confirmPasswordError.value = result.getErrorMessage()
    }
    
    fun updatePhone(phone: String) {
        delegate.phone = phone
        val result = delegate.validatePhone()
        _phoneError.value = result.getErrorMessage()
    }
    
    fun updateSelectedGenres(genres: MutableList<GameGenre>) {
        delegate.selectedGenres = genres
        val result = delegate.validateGenres()
        _genreError.value = result.getErrorMessage()
    }
    
    fun addGenre(genre: GameGenre) {
        delegate.selectedGenres.add(genre)
        val result = delegate.validateGenres()
        _genreError.value = result.getErrorMessage()
    }
    
    fun removeGenre(genre: GameGenre) {
        delegate.selectedGenres.remove(genre)
        val result = delegate.validateGenres()
        _genreError.value = result.getErrorMessage()
    }
    
    fun attemptRegistration() {
        if (delegate.isFormValid()) {
            val result = delegate.registerUser()
            _registrationResult.value = result
        } else {
            val validations = delegate.validateAllFields()
            _fullNameError.value = validations["fullName"]?.getErrorMessage()
            _emailError.value = validations["email"]?.getErrorMessage()
            _passwordError.value = validations["password"]?.getErrorMessage()
            _confirmPasswordError.value = validations["confirmPassword"]?.getErrorMessage()
            _phoneError.value = validations["phone"]?.getErrorMessage()
            _genreError.value = validations["genres"]?.getErrorMessage()
        }
    }
}
