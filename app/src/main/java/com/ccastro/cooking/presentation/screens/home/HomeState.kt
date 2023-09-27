package com.ccastro.cooking.presentation.screens.home

import com.ccastro.cooking.domain.models.Receta

data class HomeState(
    var recetas: List<Receta> = emptyList()
)
