package com.ccastro.cooking.utils

import com.ccastro.cooking.core.Constants.gson
import com.ccastro.cooking.domain.models.Ingrediente
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta

object Utils {

    val recetaOkMock = Receta(
        id = 1,
        nombre = "Tacu Tacu",
        imagenes = listOf(
            "https://i.postimg.cc/yY1FhKjb/tacucu-tacucu-1.jpg",
            "https://i.postimg.cc/cL6wjcV7/tacucu-tacucu-2.jpg",
            "https://i.postimg.cc/280n2tX8/tacucu-tacucu-3.jpg"
        ),
        location = Location(
            urlImgBandera = "https://media.istockphoto.com/id/967321126/es/vector/vector-de-bandera-del-per%C3%BA-proporci%C3%B3n-2-3-bandera-bicolor-nacional-peruana.jpg?s=1024x1024&w=is&k=20&c=MX21eYwejH9ob5FAAg9uU9Zj_U9qxoZVKAgzHHQM7ro=",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        ),
        ingredientes = listOf(
            Ingrediente(nombre= "Arroz cocido", cantidad= "2 tazas"),
            Ingrediente(nombre= "Frijoles canarios cocidos", cantidad= "1 taza"),
            Ingrediente(nombre= "Aceite vegetal", cantidad= "3 cucharadas"),
            Ingrediente(nombre= "Ajo picado", cantidad= "3 dientes"),
            Ingrediente(nombre= "Cebolla picada", cantidad= "1 unidad"),
            Ingrediente(nombre= "Pimiento rojo picado", cantidad= "1 unidad"),
            Ingrediente(nombre= "Comino molido", cantidad= "1 cucharadita"),
            Ingrediente(nombre= "Pimienta negra molida", cantidad= "al gusto"),
            Ingrediente(nombre= "Huevos", cantidad= "2 unidades"),
            Ingrediente(nombre= "Perejil picado", cantidad= "2 cucharadas")
        ),
        preparacion = "1. En una sartén, calentar el aceite y freír el ajo picado, la cebolla picada y el pimiento rojo picado hasta que estén dorados.\n2. Agregar los frijoles canarios cocidos y cocinar por unos minutos. Luego, agregar el arroz cocido y mezclar bien.\n3. Condimentar con comino, pimienta negra y sal al gusto. Continuar cocinando hasta que todo esté bien mezclado y caliente.\n4. En otra sartén, hacer huevos fritos y servir sobre el tacu tacu.\n5. Espolvorear con perejil picado antes de servir.",
        descripcion = "El \"Tacu Tacu\" es un plato típico de la comida peruana que combina arroz y frijoles refritos. Se sazona con ajo, cebolla y especias, y puede incluir ingredientes adicionales como carne, mariscos o huevo. Se sirve a menudo como guarnición o plato principal y es conocido por su sabor reconfortante y textura suave.",
        favorito = false
    )

    val RecetaEmptyMock = Receta()

    val recetaModelParcedToJsonMock = gson.toJson(recetaOkMock)

    val locationMock = Location(
        urlImgBandera = "https://media.istockphoto.com/id/967321126/es/vector/vector-de-bandera-del-per%C3%BA-proporci%C3%B3n-2-3-bandera-bicolor-nacional-peruana.jpg?s=1024x1024&w=is&k=20&c=MX21eYwejH9ob5FAAg9uU9Zj_U9qxoZVKAgzHHQM7ro=",
        pais = "Perú",
        lat = -11.1605893,
        long = -75.995834,
        regionName = "Junín"
    )

    val ingredientesMock = listOf(
        Ingrediente(nombre= "Arroz cocido", cantidad= "2 tazas"),
        Ingrediente(nombre= "Frijoles canarios cocidos", cantidad= "1 taza"),
        Ingrediente(nombre= "Aceite vegetal", cantidad= "3 cucharadas"),
        Ingrediente(nombre= "Ajo picado", cantidad= "3 dientes"),
        Ingrediente(nombre= "Cebolla picada", cantidad= "1 unidad"),
        Ingrediente(nombre= "Pimiento rojo picado", cantidad= "1 unidad"),
        Ingrediente(nombre= "Comino molido", cantidad= "1 cucharadita"),
        Ingrediente(nombre= "Pimienta negra molida", cantidad= "al gusto"),
        Ingrediente(nombre= "Huevos", cantidad= "2 unidades"),
        Ingrediente(nombre= "Perejil picado", cantidad= "2 cucharadas")
    )

}