package com.ccastro.cooking.data.repositories

import android.util.Log
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.data.api.recetas.RecetaApiDAO
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import com.ccastro.cooking.data.mappers.RecetaMapper.mapRecetaApiDTOToReceta
import com.ccastro.cooking.data.mappers.RecetaMapper.mapRecetaDBEntityToReceta
import com.ccastro.cooking.data.mappers.RecetaMapper.mapRecetaToRecetaDBEntity
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class RecetaRepositoryImplement @Inject constructor(
    @Named("RecetaApiDAO") private val recetaApiDAO: RecetaApiDAO,
    private val recetaDbDAO: RecetaDAO
    ) : IRecetaRepository {

    override suspend fun obtenerTodasLasRecetas(): Flow<List<Receta>> {

        val totalRegistrosEnBD: Int

        try {

            Log.i(TAG, "obtenerTodasLasRecetas: Contando registros")
            totalRegistrosEnBD = contarRecetas()
            Log.i(TAG, "obtenerTodasLasRecetas: TotalRegistros: $totalRegistrosEnBD")

               return if(totalRegistrosEnBD != 0) {
                    Log.i(TAG, "obtenerTodasLasRecetas: Recetas obtenidas desde la base de datos")
                    obtenerDesdeBD()

                } else {
                    Log.i(TAG, "obtenerTodasLasRecetas: Recetas obtenidas desde el API")
                    persistenciaLocal(obtenerDesdeAPI())
                    obtenerDesdeBD()
                }

        } catch (e: Exception) {
                Log.e(TAG, "Error al obtener recetas: ${Log.getStackTraceString(e)}")
                return flow { }
        }

    }

    override suspend fun obtenerDesdeBD(): Flow<List<Receta>> {
        val listaRecetas: MutableList<Receta> = mutableListOf()

        return flow<List<Receta>> {
            recetaDbDAO.getAll().collect {
                it.map {recetaEntity ->
                    listaRecetas.add(mapRecetaDBEntityToReceta(recetaEntity))
                }
                emit(listaRecetas.toList())
            }
        }
    }

    private suspend fun obtenerDesdeAPI(): Flow<List<Receta>> {
        return flow<List<Receta>> {
            emit(
                recetaApiDAO.getAll().map {recetaEntity ->
                    mapRecetaApiDTOToReceta(recetaEntity)
                }
            )
        }
    }

    override suspend fun actualizarFavorito(receta: Receta) {
        recetaDbDAO.updateFavorito(id = receta.id, favorito = receta.favorito)
    }

    private suspend fun contarRecetas(): Int {
        return recetaDbDAO.countRecetas()
    }

    private suspend fun persistenciaLocal(recetas: Flow<List<Receta>>) {

        Log.i(TAG,"Iniciando Persistencia local")
        try {
            recetas.collectLatest {
                    listRecetas ->
                listRecetas.map {
                    recetaDbDAO.insert(mapRecetaToRecetaDBEntity(it))
                    Log.i(TAG,"Receta ${it.nombre} almacenada!")
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "persistenciaLocal: ${Log.getStackTraceString(e)}")
        }
    }

}


