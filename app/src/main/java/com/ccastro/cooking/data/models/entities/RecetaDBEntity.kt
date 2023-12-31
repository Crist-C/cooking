package com.ccastro.cooking.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ccastro.cooking.domain.models.Ingrediente
import com.ccastro.cooking.domain.models.Location

@Entity(tableName = "recetas")
data class RecetaDBEntity(
    @PrimaryKey(autoGenerate = false) val idReceta: Int,
    val nombreReceta: String,
    val imagenes: List<String>,
    val localizacion: Location,
    val ingredientes: List<Ingrediente>,
    val procedimiento: String,
    val resumen: String,
    val favorito: Boolean
)
