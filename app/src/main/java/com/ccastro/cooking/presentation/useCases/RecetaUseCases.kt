package com.ccastro.cooking.presentation.useCases

import com.ccastro.cooking.domain.models.Receta

interface RecetaUseCases {

    suspend fun getAll(): List<Receta>

    suspend fun getOneById(id: Int): Receta


}