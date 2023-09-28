package com.ccastro.cooking.data.repositories

import android.util.Log
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.data.api.recetas.RecetaApiDAO
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import com.ccastro.cooking.data.mappers.RecetaMapper
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class RecetaRepositoryImplement @Inject constructor(
    @Named("RecetaApiDAO") private val recetaApiDAO: RecetaApiDAO,
    @Named("RecetaDbDAO") private val recetaDbDAO: RecetaDAO
    ) : IRecetaRepository {
    override suspend fun obtenerTodas(): List<Receta> {

        val listaRetorno: MutableList<Receta> = mutableListOf()

        return try {
            recetaApiDAO.getAll().let {
                list ->
                repeat(list.size) {iteracion ->
                    listaRetorno.add(RecetaMapper.mapRecetaDTOToReceta(list[iteracion]))
                }
            }

            GlobalScope.launch { persistenciaLocal(listaRetorno) }

            persistenciaLocal(listaRetorno)
            return listaRetorno.toList()
        }catch (e: Exception) {
            println("$e")
            listaRetorno.toList()
        }
    }

    override suspend fun obtenerPorId(id: Int): Receta {
        val receta = RecetaMapper.mapRecetaDTOToReceta(recetaApiDAO.getRecetaById(id))
        GlobalScope.launch { persistenciaLocal(listOf(receta)) }
        return receta
    }

    suspend fun persistenciaLocal(recetas: List<Receta>) {
        Log.i(TAG,"Iniciando Persistencia local")
        recetas.map {
            receta: Receta -> recetaDbDAO.insert(
                RecetaMapper.mapRecetaToRecetaDBEntity(receta)
            )
            Log.i(TAG,"Receta ${receta.nombre} almacenada!")
            delay(200L)
        }
    }

}