package com.example.gamezone.viewModel


import androidx.lifecycle.ViewModel
import com.example.gamezone.data.FakeDatabase
import com.example.gamezone.models.User

class RegisterViewModel : ViewModel() {

    fun registrar(nombre: String, correo: String, contrasena: String): String {
        if (nombre.isBlank() || correo.isBlank() || contrasena.isBlank())
            return "Completa todos los campos"

        if (!correo.endsWith("@duoc.cl"))
            return "El correo debe terminar en @duoc.cl"

        if (FakeDatabase.existeCorreo(correo))
            return "Este correo ya está registrado"

        val user = User(nombre, correo, contrasena)
        FakeDatabase.agregarUsuario(user)
        return "Usuario registrado correctamente"
    }
}
