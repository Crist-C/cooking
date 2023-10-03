package com.ccastro.cooking.presentation.screens.map

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ccastro.cooking.core.Constants
import com.ccastro.cooking.core.Utils
import com.ccastro.cooking.core.Utils.parceRecetaJsonToReceta
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(): ViewModel(){

    lateinit var latLng: LatLng
    lateinit var receta: Receta

    fun setReceta(recetaJson: String) {
        receta = parceRecetaJsonToReceta(recetaJson)
        Log.i(Constants.TAG, "receta VMMapa: ${receta.location.pais} ${receta.location.lat} ${receta.location.long}")
    }

    fun updateMarkerLatLng(location: Location) {
        latLng = LatLng(location.lat, location.long)
    }


}