package com.ccastro.cooking.presentation.useCases

import com.ccastro.cooking.domain.models.Receta
import kotlinx.coroutines.flow.Flow

interface RecetaUseCases {

    suspend fun getAll(): List<Receta>

    suspend fun getOneById(id: Int): Receta


}