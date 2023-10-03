package com.ccastro.cooking.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
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
class HomeViewModel @Inject constructor(@Named("RecetasCasosDeUso")private val recetasUseCases: RecetaUseCases ): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _recetas = MutableStateFlow(listOf(Receta()))

    @OptIn(FlowPreview::class)
    val recetas = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_recetas) { text, recetas ->
            if(text.isBlank()) {
                recetas
            } else {
                delay(2000L)
                Log.i(TAG, "Flitrado de recetas: ${recetas.size} : $recetas")
                recetas.filter {
                    it.encontrarCoincidencia(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _recetas.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    init {
        actualizarRecetas()
    }

    private fun actualizarRecetas() {

        viewModelScope.launch{
            recetasUseCases.getAll().collect {
                _recetas.value = it
            }
        }

    }

    fun actualizarFavorito(receta: Receta) {
        viewModelScope.launch { recetasUseCases.updateFavorite(receta) }
    }

}