package com.ccastro.cooking.presentation.screens.details

import androidx.lifecycle.ViewModel
import com.ccastro.cooking.data.mappers.RecetaMapper.parceRecetaJsonToReceta
import com.ccastro.cooking.domain.models.Receta
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetalisViewModel @Inject constructor(): ViewModel() {

    lateinit var receta: Receta

    fun setReceta(recetaJson: String) {
        receta = parceRecetaJsonToReceta(recetaJson)
    }

}