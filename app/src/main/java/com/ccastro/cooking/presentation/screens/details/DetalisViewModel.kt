package com.ccastro.cooking.presentation.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccastro.cooking.domain.models.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class DetalisViewModel @Inject constructor(): ViewModel() {
    fun cargarReceta(): Receta {
        val receta = viewModelScope.async {

        }
        return state.receta
    }

    var state by mutableStateOf(DetailsState())
}