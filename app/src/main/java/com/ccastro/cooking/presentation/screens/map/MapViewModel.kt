package com.ccastro.cooking.presentation.screens.map

import androidx.lifecycle.ViewModel
import com.ccastro.cooking.domain.models.Location
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(): ViewModel(){

    lateinit var location: Location
    lateinit var latLng: LatLng

    fun parceStringToLocation(locationString: String?) {
        val gson = Gson()
        location = gson.fromJson(locationString, object : TypeToken<Location>(){}.type)
    }

    init {

    }

    fun updateMarkerLatLng(location: Location) {
        latLng = LatLng(location.lat, location.long)
    }

    /*
    //val singapore = LatLng(1.35, 103.87)
    var singaporeState: MarkerState? = null

    init {
        state = state.copy(
            placesList = mutableListOf(places.iterator().next())
        )
        singaporeState = MarkerState(position = LatLng(state.placesList[0].lat, state.placesList[0].lon))
    }

     */


}