package com.ccastro.cooking.data.repositories

import com.ccastro.cooking.data.api.recetas.RecetaDAO
import com.ccastro.cooking.data.mappers.RecetaMapper
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import javax.inject.Inject
import javax.inject.Named

class IRecetaRepositoryImplement @Inject constructor(
    @Named("RecetaDAO") private val recetaDAO: RecetaDAO,

    ) : IRecetaRepository {
    override suspend fun obtenerTodas(): List<Receta> {

        val listaRetorno: MutableList<Receta> = mutableListOf()

        return try {
            recetaDAO.getAll().let {
                list ->
                repeat(list.size) {iteracion ->
                    listaRetorno.add(RecetaMapper.mapRecetaDTOToReceta(list[iteracion]))
                }
            }
            return listaRetorno.toList()
        }catch (e: Exception) {
            println("$e")
            listaRetorno.toList()
        }
    }

    override suspend fun obtenerPorId(id: Int): Receta {
        return RecetaMapper.mapRecetaDTOToReceta(recetaDAO.getRecetaById(id))
    }

}