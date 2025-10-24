package com.example.gamezone.viewModel



import androidx.lifecycle.ViewModel
import com.example.gamezone.data.FakeDatabase

class LoginViewModel : ViewModel() {

    fun login(correo: String, contrasena: String): String {
        if (correo.isBlank() || contrasena.isBlank())
            return "Completa todos los campos"

        val usuario = FakeDatabase.buscarUsuario(correo, contrasena)
        return if (usuario != null) "ok" else "Correo o contraseña incorrectos"
    }
}
