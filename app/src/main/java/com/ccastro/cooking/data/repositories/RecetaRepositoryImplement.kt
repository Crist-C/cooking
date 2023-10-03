package com.ccastro.cooking.data.repositories

import android.util.Log
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.data.api.recetas.RecetaApiDAO
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import com.ccastro.cooking.data.mappers.RecetaMapper.mapRecetaApiDTOToReceta
import com.ccastro.cooking.data.mappers.RecetaMapper.mapRecetaToRecetaDBEntity
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.domain.repositories.IRecetaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class RecetaRepositoryImplement @Inject constructor(
    @Named("RecetaApiDAO") private val recetaApiDAO: RecetaApiDAO,
    private val recetaDbDAO: RecetaDAO
    ) : IRecetaRepository {

        override suspend fun obtenerTodasLasRecetas(): Flow<List<Receta>> {

        val listaFromAPI: Flow<List<Receta>> = obtenerRecetasDesdeAPI()
        //val listaFromDB: Flow<List<Receta>> = obtenerRecetasDesdeDB()

        var recetasDesdeApi = 0
        //var recetasDesdeDB = 0


        try {
/*
            //Obteniendo desde la BD
            listaFromDB.collect {
                recetasDesdeDB = it.size
            }
            if(recetasDesdeDB != 0) {
                Log.i(TAG, "obtenerTodasLasRecetas: $recetasDesdeDB Recetas obtenidas desde la base de datos")
                return listaFromDB
            }
            Log.i(TAG, "obtenerTodasLasRecetas: No existen registros la base de datos!")
*/

            //Obteniendo desde el API
            listaFromAPI.collect{
                recetasDesdeApi = it.size
            }

            if (recetasDesdeApi != 0) {
                Log.i(TAG, "obtenerTodasLasRecetas: $recetasDesdeApi Recetas obtenidas desde el API")
                GlobalScope.launch { persistenciaLocal(listaFromAPI) }
                return listaFromAPI
            }
            Log.i(TAG, "obtenerTodasLasRecetas: No se obtuvieron Recetas desde el API!")


        } catch (e: Exception) {
                Log.e(TAG, "Error al obtener recetas: ${Log.getStackTraceString(e)}")
                return flow { }
        }

        Log.e(TAG, "Error al abtener recetas: Sin respuesta")
        return listaFromAPI
    }

    private suspend fun obtenerRecetasDesdeAPI(): Flow<List<Receta>> {
        Log.i(TAG, "obtenerRecetasDesdeAPI: obteniendo desde API")
        val listaRetorno: MutableList<Receta> = mutableListOf()
        recetaApiDAO.getAll().map { listaRetorno.add( mapRecetaApiDTOToReceta(it) ) }
        return flow{emit(listaRetorno)}
    }

    suspend fun obtenerRecetasDesdeDB(): Flow<List<Receta>> {
        Log.i(TAG, "obtenerRecetasDesdeDB: Obteniendo desde BD")
        val listaRetorno: List<Receta> = emptyList()

        GlobalScope.launch { recetaDbDAO.getAll() }


        Log.i(TAG, "Lista de retorno contiene : ${listaRetorno.size} elementos")
        return flow{emit(listaRetorno.toList()) }
    }

    override suspend fun obtenerPorId(id: Int): Receta {
        val receta = mapRecetaApiDTOToReceta(recetaApiDAO.getById(id))
        GlobalScope.launch { persistenciaLocal( flow{ emit(listOf(receta)) } ) }
        return receta
    }

    override suspend fun actualizarFavorito(receta: Receta) {
        recetaDbDAO.updateFavorito(id = receta.id, favorito = receta.favorito)
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
            Log.e(TAG, "persistenciaLocal: ${Log.getStackTraceString(e)}", )
        }
    }

}