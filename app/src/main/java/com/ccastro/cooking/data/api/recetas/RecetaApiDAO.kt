package com.ccastro.cooking.data.api.recetas

import com.ccastro.cooking.data.models.dto.RecetaApiDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecetaApiDAO {

    @GET("/listarRecetas")
    suspend fun getAll(): List<RecetaApiDTO>

    @GET("/obtenerReceta/id={id}")
    suspend fun getById(@Path("id") id: Int): RecetaApiDTO

}