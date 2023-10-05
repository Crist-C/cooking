package com.ccastro.cooking.domain.models

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IngredienteTest {

    @Test
    fun isValid_DeberiaDevolverVerdaderoCuandoLaInstanciaEsValida() {
        val ingrediente = Ingrediente(
            nombre = "Arroz cocido",
            cantidad = "2 tazas"
        )

        val resultado = ingrediente.isValid()

        assertThat(resultado).isTrue()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoElNombreEstaEnBlanco() {
        val ingrediente = Ingrediente(
            nombre = "",
            cantidad = "2 tazas"
        )

        val resultado = ingrediente.isValid()

        assertThat(resultado).isFalse()
    }

    @Test
    fun isValid_DeberiaDevolverFalsoCuandoLaCantidadEstaEnBlanco() {
        val ingrediente = Ingrediente(
            nombre = "Arroz cocido",
            cantidad = ""
        )

        val resultado = ingrediente.isValid()

        assertThat(resultado).isFalse()
    }

    // Continúa con más casos de prueba según las validaciones
}
