package com.ccastro.cooking.data.models.dto

import com.ccastro.cooking.domain.models.Ingrediente
import com.ccastro.cooking.domain.models.Location

data class RecetaApiDTO(
    val id: Int,
    val nombreReceta: String,
    val imagenes: List<String>,
    val localizacion: Location,
    val ingredientes: List<Ingrediente>,
    val procedimiento: String,
    val descripcion: String
)
