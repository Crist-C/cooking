package com.ccastro.cooking.domain.useCases.receta

import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class ObtenerTodas @Inject constructor(@Named("IRecetaRepository") private val IRecetaRepository: IRecetaRepository) {
    suspend operator fun invoke(): Flow<List<Receta>> = IRecetaRepository.obtenerTodasLasRecetas()
}