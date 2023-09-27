package com.ccastro.cooking.domain.repositories

import com.ccastro.cooking.domain.models.Receta

interface IRecetaRepository {
    suspend fun obtenerTodas(): List<Receta>

    suspend fun obtenerPorId(id: Int): Receta

}