package com.ccastro.cooking.presentation.screens.map


import com.ccastro.cooking.utils.Utils.locationMock
import com.ccastro.cooking.utils.Utils.recetaModelParcedToJsonMock
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MapViewModelTest {


    private lateinit var mapViewModel: MapViewModel

    @Before
    fun setUp() {
        mapViewModel = MapViewModel()
    }

    @Test
    fun setReceta_shouldSetReceta() {

        mapViewModel.setReceta(recetaModelParcedToJsonMock)

        assertThat(mapViewModel.receta.id).isEqualTo(1)
        assertThat(mapViewModel.receta.nombre).containsMatch("Tacu Tacu")

    }

    @Test
    fun updateMarkerLatLng_shouldUpdateLatLng() {

        mapViewModel.updateMarkerLatLng(locationMock)

        assertThat(mapViewModel.latLng.latitude).isEqualTo(-11.1605893)
        assertThat(mapViewModel.latLng.longitude).isEqualTo(-75.995834)
    }

}