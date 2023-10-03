package com.ccastro.cooking.presentation.screens.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.cooking.core.Constants.TAG
import com.ccastro.cooking.presentation.components.genericos.IconBackArrow
import com.ccastro.cooking.presentation.components.genericos.TittleSecondaryBoldText
import com.ccastro.cooking.presentation.components.genericos.TittleText
import com.ccastro.cooking.presentation.navigation.AppScreens
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapContent(navHost: NavHostController, viewModel: MapViewModel = hiltViewModel()) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            viewModel.latLng,
            //LatLng(viewModel.latLng.latitude, viewModel.latLng.latitude),
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