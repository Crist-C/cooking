package com.ccastro.cooking.data.api.recetas

import com.ccastro.cooking.data.models.dto.RecetaApiDTO
import retrofit2.http.GET

interface RecetaApiDAO {

    @GET("/listarRecetas")
    suspend fun getAll(): List<RecetaApiDTO>

}