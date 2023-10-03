package com.ccastro.cooking.domain.repositories

import com.ccastro.cooking.domain.models.Receta
import kotlinx.coroutines.flow.Flow

interface IRecetaRepository {
    suspend fun obtenerTodasLasRecetas(): Flow<List<Receta>>

    suspend fun obtenerDesdeBD(): Flow<List<Receta>>

    suspend fun actualizarFavorito(receta: Receta)

}