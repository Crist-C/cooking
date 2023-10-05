package com.ccastro.cooking

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Convención para el nombramiento de código:
 *
 *      1.  nombreDelMetodo_Escenario_ComportamientoEsperado
 *      ej:
 *      multiplicar_PositivoYNegativo_resultadoNegativo()
 *
 *      2. Debe_ResultarNegativo_Cuando_MultiplicamosPositivoYNegativo
 *      ej: debe_resultarNegativo_cuando_multiplicamosPositivoYNegativo
 *
 *      3. Técnica Dado - Cuando - Entonces ()
 *      Dado_EstadoInicial_Cuando_EscenarioATestear_Entonces_ComportamientoEsperado
 *      ej:
 *      Dados_NumerosPositivoYNegativo_Cuando_Multiplicamos_Entonces_ResultadoEsNegativo
 *
 *      4. Qué estamos testeando
 *
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun sustraction_isCorrect() {
        assertEquals(0, 2 - 2 )
        val resultado = true
    }

}