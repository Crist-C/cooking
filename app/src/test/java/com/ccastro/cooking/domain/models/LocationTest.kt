package com.ccastro.cooking.domain.models

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LocationTest {

    @Test
    fun isValid_DeberiaDevolverVerdaderoCuandoLaInstanciaEsValida() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isTrue()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaUrlEsInvalida() {
        val location = Location(
            urlImgBandera = "invalid-url",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoElPaisEstaEnBlanco() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }


    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaRegionEstaEnBlanco() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = ""
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaLatitudEsInvalida() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -91.0,
            long = -75.995834,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaLongitudEsInvalida() {
        val location = Location(
            urlImgBandera = "https://example.com/flag.jpg",
            pais = "Perú",
            lat = -11.1605893,
            long = -181.0,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaUrlEsVacia() {
        val location = Location(
            urlImgBandera = "",
            pais = "Perú",
            lat = -11.1605893,
            long = -75.995834,
            regionName = "Junín"
        )

        val resultado = location.isValid()

        assertThat(resultado).isFalse()
    }

}