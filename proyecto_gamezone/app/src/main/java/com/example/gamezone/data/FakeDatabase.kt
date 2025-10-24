package com.example.gamezone.data

import com.example.gamezone.models.User

object FakeDatabase {
    val usuarios = mutableListOf<User>()

    fun agregarUsuario(usuario: User) {
        usuarios.add(usuario)
    }

    fun buscarUsuario(correo: String, contrasena: String): User? {
        return usuarios.find { it.correo == correo && it.contrasena == contrasena }
    }

    fun existeCorreo(correo: String): Boolean {
        return usuarios.any { it.correo == correo }
    }
}