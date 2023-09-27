package com.ccastro.cooking.domain.models

data class Receta(
    val nombre: String,
    val imagenes: List<String>,
    val location: Location,
    val ingredientes: Ingredientes,
    val preparacion: String
)
