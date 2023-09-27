package com.ccastro.cooking.data.api.recetas

import com.ccastro.cooking.data.models.RecetaDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecetaDAO {

    @GET("/listarRecetas")
    suspend fun getAll(): List<RecetaDTO>

    @GET("/obtenerReceta/id={recetaId}")
    suspend fun getRecetaById(@Path("recetaId") recetaId: Int): RecetaDTO

}