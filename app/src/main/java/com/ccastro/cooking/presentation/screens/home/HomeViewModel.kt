package com.ccastro.cooking.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(@Named("RecetasCasosDeUso")private val recetasUseCases: RecetaUseCases ): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        actualizarRecetas()
    }

    private fun actualizarRecetas() {

        val obtenerRecetasCoroutine = viewModelScope.async{
            recetasUseCases.getAll().collect(){
                state = state.copy( recetas = it)
            }
        }

    }

}