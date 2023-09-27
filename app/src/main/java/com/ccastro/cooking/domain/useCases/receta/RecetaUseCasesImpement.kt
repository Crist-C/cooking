package com.ccastro.cooking.domain.useCases.receta

import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import kotlinx.coroutines.flow.Flow

data class RecetaUseCasesImpement(
    val obtenerPorId: ObtenerUnaPorId,
    val obtenerTodas: ObtenerTodas
): RecetaUseCases {
    override suspend fun getAll(): List<Receta> {
        return obtenerTodas.invoke()
    }

    override suspend fun getOneById(id: Int): Receta {
        return obtenerPorId.invoke(id)
    }
}
