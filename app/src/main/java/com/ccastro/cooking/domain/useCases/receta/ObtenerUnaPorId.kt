package com.ccastro.cooking.domain.useCases.receta

import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import javax.inject.Inject
import javax.inject.Named

class ObtenerUnaPorId @Inject constructor(@Named("IRecetaRepository") private val iRecetaRepository: IRecetaRepository) {
    suspend operator fun invoke(id: Int) : Receta = iRecetaRepository.obtenerPorId(id)

}
