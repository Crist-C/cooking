package com.ccastro.cooking.domain.models

data class Ingredientes(
    val nombre: String,
    val cantidad: String
) {
    fun encontrarCoincidencia(valorDeBusqueda: String): Boolean {
        return nombre.contains(valorDeBusqueda, ignoreCase = true)
    }
}
