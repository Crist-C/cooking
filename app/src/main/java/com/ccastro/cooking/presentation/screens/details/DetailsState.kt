package com.ccastro.cooking.presentation.screens.details

import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta

data class DetailsState(
    val test: String = "",
    val receta: Receta = Receta(
        nombre = "Aji de Gallina",
        imagenes = listOf("https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp", "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp", "https://picsum.photos/id/10/500/700", "Url3"),
        location = Location("https://media.istockphoto.com/id/967321126/es/vector/vector-de-bandera-del-per%C3%BA-proporci%C3%B3n-2-3-bandera-bicolor-nacional-peruana.jpg?s=1024x1024&w=is&k=20&c=MX21eYwejH9ob5FAAg9uU9Zj_U9qxoZVKAgzHHQM7ro=","Perú",-12.046374f,-77.04279f,"Lima"),
        ingredientes = listOf(
            Ingredientes("Pechugas de pollo", "500g"),
            Ingredientes("Aji amarillo", "4 unidades"),
            Ingredientes("Queso fresco", "100g"),
            Ingredientes("Pan remojado en leche", "2 rebanadas"),
            Ingredientes("Nueces o almendras", "50g"),
            Ingredientes("Aceitunas negras", "100g"),
            Ingredientes("Papas cocidas", "4 unidades"),
            Ingredientes("Huevos cocidos", "4 unidades"),
            Ingredientes("Leche evaporada", "1 taza"),
            Ingredientes("Aceite vegetal", "3 cucharadas")
        ),
        preparacion = "1. Cocinar las pechugas de pollo y desmenuzarlas. Reservar.\\n2. Licuar los ajíes amarillos con el queso fresco, el pan remojado en leche y las nueces o almendras hasta obtener una mezcla homogénea.\\n3. En una olla, calentar el aceite y añadir la mezcla licuada. Cocinar a fuego medio-bajo, removiendo constantemente, hasta que espese.\\n4. Agregar la leche evaporada y seguir cocinando por unos minutos.\\n5. Incorporar el pollo desmenuzado y las aceitunas negras picadas.\\n6. Servir sobre papas cocidas y decorar con huevos duros cortados en rodajas.",
        descripcion = "El \"Ají de Gallina\" es una delicia típica de la región de Arequipa en Perú. Se prepara cocinando pechugas de pollo en una mezcla de aji amarillo, pan remojado en leche, nueces o almendras y aceitunas negras. Se sirve sobre papas cocidas y se decora con huevos duros y perejil picado. El resultado es una mezcla de sabores intensos y texturas deliciosas."
    )
)
