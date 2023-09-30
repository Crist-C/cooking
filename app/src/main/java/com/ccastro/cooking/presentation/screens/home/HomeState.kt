package com.ccastro.cooking.presentation.screens.home

import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta

data class HomeState(

    var recetas: List<Receta> = emptyList(),

    val receta: Receta = Receta(
    nombre = "Sancocho",
    imagenes = listOf("https://upload.wikimedia.org/wikipedia/commons/d/df/Flag_of_Peru_%28state%29.svg", "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp", "https://picsum.photos/id/10/500/700", "Url3"),
    location = Location("https://upload.wikimedia.org/wikipedia/commons/d/df/Flag_of_Peru_%28state%29.svg","Perú",7.1f,12.4f,"Arequipa"),
    ingredientes = listOf(Ingredientes("Papa", "1 libra")),
    preparacion = "Cocinar a fuego lento",
    descripcion = "El \"Sancocho\" es una deliciosa preparación peruana que consiste en una papa cocida rellena de una mezcla sazonada de carne molida, cebolla, ajo, aceitunas, huevo duro y especias. La papa rellena se empaniza y se fríe hasta que esté dorada y crujiente por fuera. Es un platillo reconfortante y sabroso."
    )
)
