package com.ccastro.cooking.presentation.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapContent(viewModel: MapViewModel = hiltViewModel()) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            viewModel.latLng,
            17f
        )
    }
    Box(modifier = Modifier.fillMaxSize()){


        GoogleMap(
            modifier = Modifier.fillMaxSize().padding(vertical = 40.dp),
            cameraPositionState = cameraPositionState
        ){
            Marker(
                state = MarkerState(viewModel.latLng),
                title = "${viewModel.receta.location.pais} - ${viewModel.receta.location.regionName}"
            )
        }

        Column(Modifier.fillMaxSize().padding(32.dp)) {

        }

    }

}