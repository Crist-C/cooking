package com.ccastro.cooking.domain.models

import com.ccastro.cooking.utils.Utils
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecetaTest {


    @Test
    fun isValid_DeberiaDevolverFalsoCuandoIdEsCero() {
        val receta = Receta(id = 0)

        val resultado = receta.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoNombreEstaEnBlanco() {
        val receta = Receta(nombre = "")

        val resultado = receta.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoImagenesContieneUrlInvalida() {
        val receta = Receta(imagenes = listOf("https://valid-url.com", "invalid-url"))

        val resultado = receta.isValid()

        assertThat(resultado).isFalse()
    }


    // Continúa con más casos de prueba según las validaciones

    @Test
    fun isValidLocation_DeberiaDevolverVerdaderoCuandoLocationEsValida() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        )
        val receta = Receta(
            id = 1,
            nombre = "Tacu Tacu",
            imagenes = listOf(
                "https://i.postimg.cc/yY1FhKjb/tacucu-tacucu-1.jpg",
                "https://i.postimg.cc/cL6wjcV7/tacucu-tacucu-2.jpg",
                "https://i.postimg.cc/280n2tX8/tacucu-tacucu-3.jpg"
            ),
            location = location,
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

        val resultado = receta.isValid()

        assertThat(resultado).isTrue()
    }

    @Test
    fun isValidLocation_DeberiaDevolverFalsoCuandoLocationEsInvalida() {
        val location = Location(
            urlImgBandera = "",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = ""
        )

        val receta = Receta(location = location)

        val resultado = receta.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValidLocation_DeberiaDevolverFalsoCuandoLasCoordenadasSonInvalidas() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -91.1605893,
            long = -75.995834,
            regionName = "Región"
        )

        val receta = Receta(location = location)

        assertThat(receta.isValid()).isFalse()

        receta.location.lat = 91.1605893
        assertThat(receta.isValid()).isFalse()

        receta.location.lat = 61.1605893
        assertThat(receta.location.isValid()).isTrue()

        receta.location.lat = 81.1605893
        receta.location.long = 181.1605893
        assertThat(receta.isValid()).isFalse()

        receta.location.long = -181.1605893
        assertThat(receta.isValid()).isFalse()

        receta.location.long = -168.1605893
        assertThat(receta.location.isValid()).isTrue()
    }


    @Test
    fun contieneNombre_DeberiaDevolverVerdaderoCuandoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneNombre("acu")

        assertThat(resultado).isTrue()
    }

    @Test
    fun contieneNombre_DeberiaDevolverFalsoCuandoNoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneNombre("no-coincide")

        assertThat(resultado).isFalse()
    }

    @Test
    fun contieneIngredientes_DeberiaDevolverVerdaderoCuandoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneIngredientes("Arroz cocido")

        assertThat(resultado).isTrue()
    }

    @Test
    fun contieneIngredientes_DeberiaDevolverFalsoCuandoNoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneIngredientes("No coincide")

        assertThat(resultado).isFalse()
    }

    @Test
    fun contienePais_DeberiaDevolverVerdaderoCuandoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contienePais("Perú")

        assertThat(resultado).isTrue()
    }

    @Test
    fun contienePais_DeberiaDevolverFalsoCuandoNoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contienePais("Otro País")

        assertThat(resultado).isFalse()
    }

    @Test
    fun contieneRegion_DeberiaDevolverVerdaderoCuandoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneRegion("Junín")

        assertThat(resultado).isTrue()
    }

    @Test
    fun contieneRegion_DeberiaDevolverFalsoCuandoNoEncuentraCoincidencia() {
        val receta = Utils.recetaOkMock

        val resultado = receta.contieneRegion("Otra Región")

        assertThat(resultado).isFalse()
    }

}
