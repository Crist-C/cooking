package com.ccastro.cooking.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("RecetasCasosDeUso") private val recetasUseCases: RecetaUseCases ): ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _filtros = MutableStateFlow(mutableListOf<Receta.Filtros>(Receta.Filtros.Ninguno))
    private var filtros = _filtros.asStateFlow()

    private val _recetas = MutableStateFlow(listOf(Receta()))

    @OptIn(FlowPreview::class)
    val recetas = filtros
        .onEach {

            mostrarProgressBar(true)
            actualizarRecetas()

        }
        .combine(_recetas.combine(_searchText) {

            recetas: List<Receta>, texto: String ->
            encontrarRecetaPor(recetas, filtros.value, texto)

        }) {

            _: MutableList<Receta.Filtros>, recetasFiltradas: List<Receta> -> recetasFiltradas.distinct()

        }
        .debounce(500L)
        .onEach {

            mostrarProgressBar(false)

        }
        .stateIn( viewModelScope, SharingStarted.Lazily, _recetas.value )


    private fun actualizarRecetas() {
        viewModelScope.launch{
            recetasUseCases.getAll().collect {
                _recetas.value = it
            }
        }
    }

    private fun encontrarRecetaPor(recetas: List<Receta>, filtros: List<Receta.Filtros>, value: Any) : List<Receta>{

        var listResultante: List<Receta> = listOf()

        if ( value.toString().isEmpty() && !filtros.contains(Receta.Filtros.Favorito)) return recetas

        filtros.map { Log.i(TAG, "encontrarRecetasPor: ${it.filtro} : $value") }

        filtros.map{filtro ->
            listResultante = (
                    listResultante +
                            recetas.filter { receta -> receta.filterBy(filtro, value)
                            })
        }
        return listResultante.distinct()
    }

    private fun mostrarProgressBar(mostrar: Boolean) {
        _isSearching.update { mostrar }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun agregarEliminarFiltro(filtro: Receta.Filtros) {

        val filtrosActuales = filtros
        actualizarRecetas()

        if (!filtrosActuales.value.contains(filtro)) {

            filtrosActuales.value.add(filtro)
            if(filtrosActuales.value.contains(Receta.Filtros.Ninguno)){
                filtrosActuales.value.remove(Receta.Filtros.Ninguno)
            }

        } else {
            filtrosActuales.value.remove(filtro)
            if(filtrosActuales.value.isEmpty()){
                filtrosActuales.value.add(Receta.Filtros.Ninguno)
            }
        }
        _filtros.update {
            filtrosActuales.value
        }
        //_recetas.value = encontrarRecetaPor(recetas.value,filtros.value,searchText.value)
    }

    fun actualizarFavorito(receta: Receta, value: Boolean) {
        receta.favorito = value
        viewModelScope.launch { recetasUseCases.updateFavorite(receta) }
        actualizarRecetas()
    }

}