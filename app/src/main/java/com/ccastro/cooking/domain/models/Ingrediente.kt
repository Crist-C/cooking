package com.ccastro.cooking.domain.models

data class Ingrediente(
    val nombre: String,
    val cantidad: String
) {

    fun isValid(): Boolean {
        return nombre.isNotEmpty() && cantidad.isNotEmpty()
    }

    fun contieneNombre(valorDeBusqueda: String): Boolean {
        return nombre.contains(valorDeBusqueda, ignoreCase = true)
    }
}
