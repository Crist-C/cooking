package com.ccastro.cooking.domain.useCases.receta

import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.useCases.RecetaUseCases
import kotlinx.coroutines.flow.Flow

data class RecetaUseCasesImpement(
    val obtenerPorId: ObtenerUnaPorId,
    val obtenerTodas: ObtenerTodas,
    val actualizarFavorito: ActualizarFavorito
): RecetaUseCases {
    override suspend fun getAll(): Flow<List<Receta>> {
        return obtenerTodas.invoke()
    }

    override suspend fun getOneById(id: Int): Receta {
        return obtenerPorId.invoke(id)
    }

    override suspend fun updateFavorite(receta: Receta) {
        actualizarFavorito.invoke(receta)
    }


}
