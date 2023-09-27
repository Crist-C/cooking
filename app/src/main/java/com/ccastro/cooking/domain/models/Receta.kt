package com.ccastro.cooking.domain.models

data class Receta(
    val id: Int = 0,
    val nombre: String,
    val imagenes: List<String>,
    val location: Location,
    val ingredientes: List<Ingredientes>,
    val preparacion: String
)
