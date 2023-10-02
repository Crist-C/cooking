package com.ccastro.cooking.presentation.screens.map

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navHostController: NavHostController,
    location: String?, viewModel:
    MapViewModel = hiltViewModel()
) {
    location?.let {
        viewModel.parceStringToLocation(location)
        viewModel.updateMarkerLatLng(viewModel.location)
    }

    Scaffold (
        topBar = {},
        content = {
            MapContent(navHostController)
        },
        bottomBar = {}
    )
}