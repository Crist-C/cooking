package com.ccastro.cooking.domain.models

import android.util.Log
import com.ccastro.cooking.core.Constants.TAG
import java.lang.Enum

class Receta(
    val id: Int = 0,
    val nombre: String = "",
    val imagenes: List<String> = emptyList(),
    val location: Location = Location("","", 0.0,0.0,""),
    val ingredientes: List<Ingrediente> = emptyList(),
    val preparacion: String = "",
    val descripcion: String = "",
    var favorito: Boolean = false
) {

    // ------------------- MÉTODOS PARA LA CONSULTA DE COINCIDENCIAS ------------------ //
    fun encontrarCoincidencia(valorDeBusqueda: String): Boolean {

        Log.i(TAG, "encontrarCoincidencia: $valorDeBusqueda")

        if (contieneNombre(valorDeBusqueda)) return true

        Log.i(TAG, "No se encontró en nombre: $valorDeBusqueda")
        if (contieneIngredientes(valorDeBusqueda)) return true

        Log.i(TAG, "no se encontró en ingredientes: $valorDeBusqueda")
        if (contienePais(valorDeBusqueda)) return true

        Log.i(TAG, "no se encontró en País: $valorDeBusqueda")
        return contieneRegion(valorDeBusqueda)
    }

    fun contieneNombre(valorDeBusqueda: String): Boolean {
        return nombre.contains(valorDeBusqueda, ignoreCase = true)
    }

    fun contieneIngredientes(valorDeBusqueda: String): Boolean {
        return ingredientes.any { it.contieneNombre(valorDeBusqueda) }
    }

    fun contienePais(valorDeBusqueda: String): Boolean {
        return location.pais.contains(valorDeBusqueda, ignoreCase = true)
    }

    fun contieneRegion(valorDeBusqueda: String): Boolean {
        return location.regionName.contains(valorDeBusqueda, ignoreCase = true)
    }

    // ------------- MÉTODOS DE VALIDACIÓN DE CAMPOS ----------------- //

    fun isValid(): Boolean {
        return isValidId() &&
                isValidNombre() &&
                isValidImagenes() &&
                location.isValid() &&
                isValidIngredientes() &&
                preparacion.isNotBlank() &&
                descripcion.isNotBlank()
    }

    private fun isValidId(): Boolean {
        return id != 0
    }

    private fun isValidNombre(): Boolean {
        return nombre.isNotBlank()
    }

    private fun isValidImagenes(): Boolean {
        return imagenes.all { isValidUrl(it) }
    }

    private fun isValidUrl(url: String): Boolean {
        return url.startsWith("https://")
    }

    private fun isValidIngredientes(): Boolean {
        return ingredientes.all { it.isValid() }
    }

    private fun isThisFavorite() : Boolean {
        return favorito
    }

    fun filterBy(filtro: Filtros, value: Any): Boolean {
        Log.i(TAG, "filterBy: ${filtro.filtro}")
        return when(filtro) {
            Filtros.Favorito -> isThisFavorite()
            Filtros.Ingredientes -> contieneIngredientes(value.toString())
            Filtros.Nombre -> contieneNombre(value.toString())
            Filtros.Region -> contieneRegion(value.toString())
            Filtros.Pais -> contienePais(value.toString())
            else -> {encontrarCoincidencia(value.toString())}
        }

    }

    // ----------- Filtros ------------- //
    sealed class Filtros(val filtro: String) {

        object Ninguno: Filtros("Ninguno")
        object Nombre: Filtros("nombre")
        object Ingredientes: Filtros("ingredientes")
        object Region: Filtros("region")
        object Pais: Filtros("pais")
        object Favorito: Filtros("favorito")
    }

}

