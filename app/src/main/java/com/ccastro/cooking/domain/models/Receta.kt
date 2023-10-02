package com.ccastro.cooking.domain.models

class Receta(
    val id: Int = 0,
    val nombre: String = "",
    val imagenes: List<String> = emptyList(),
    val location: Location = Location("","", 0.0,0.0,""),
    val ingredientes: List<Ingredientes> = emptyList(),
    val preparacion: String = "",
    val descripcion: String = "",
    var favorito: Boolean = false
) {
    fun encontrarCoincidencia(valorDeBusqueda: String): Boolean {

        if (nombre.contains(valorDeBusqueda, ignoreCase = true)) return true

        if (ingredientes.any{it.encontrarCoincidencia(valorDeBusqueda)}) return true

        if (location.pais.contains(valorDeBusqueda, ignoreCase = true)) return true

        return location.regionName.contains(valorDeBusqueda, ignoreCase = true)
    }
}

