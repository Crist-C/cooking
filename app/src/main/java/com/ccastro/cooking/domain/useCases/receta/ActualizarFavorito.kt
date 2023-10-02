package com.ccastro.cooking.domain.useCases.receta

import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import javax.inject.Inject
import javax.inject.Named

class ActualizarFavorito @Inject constructor(@Named("IRecetaRepository") private val iRecetaUseCases: IRecetaRepository) {
    suspend operator fun invoke(receta: Receta) = iRecetaUseCases.actualizarFavorito(receta)
}