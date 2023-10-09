package com.ccastro.cooking.domain.models

import android.util.Log
import com.ccastro.cooking.core.Constants.TAG

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
    private fun encontrarCoincidencia(valorDeBusqueda: String): Boolean {

        if (contieneNombre(valorDeBusqueda)) return true
        if (contieneIngredientes(valorDeBusqueda)) return true
        if (contienePais(valorDeBusqueda)) return true
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

        return when(filtro.filtro) {
            Filtros.Favorito.filtro -> {
                if(value.toString().isEmpty()){ isThisFavorite() }
                else {
                    isThisFavorite() && encontrarCoincidencia(value.toString())
                }
            }
            Filtros.Ingredientes.filtro -> contieneIngredientes(value.toString())
            Filtros.Nombre.filtro -> contieneNombre(value.toString())
            Filtros.Region.filtro -> contieneRegion(value.toString())
            Filtros.Pais.filtro -> contienePais(value.toString())
            Filtros.Ninguno.filtro -> {encontrarCoincidencia(value.toString())}
            else -> {
                Log.i(TAG, "filterBy: NO COINCIDIO CON ALGÚN FILTRO ${filtro.filtro}")
                return false
            }
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

