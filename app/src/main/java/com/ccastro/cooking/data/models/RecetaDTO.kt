package com.ccastro.cooking.data.models

import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location

data class RecetaDTO(
    val id: Int,
    val nombre_receta: String,
    val imagenes: List<String>,
    val localizacion: Location,
    val ingredientes: List<Ingredientes>,
    val procedimiento: String
)
