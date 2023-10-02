package com.ccastro.cooking.presentation.screens.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.cooking.core.Constants.TAG
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapContent(navHostController: NavHostController, viewModel: MapViewModel = hiltViewModel()) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(viewModel.location.lat, viewModel.location.long),
            17f
        )
    }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ){
            Log.i(TAG, "MarketLocation: ${viewModel.latLng.describeContents()}")
            Marker(
                state = MarkerState(viewModel.latLng),
                title = "${viewModel.location.pais} - ${viewModel.location.pais}"
            )
        }
        Text(text = "MY FIRST MAPA", style = MaterialTheme.typography.headlineMedium)
    }

}