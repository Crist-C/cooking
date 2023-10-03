package com.ccastro.cooking.presentation.screens.map

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navHost: NavHostController,
    recetaJson: String?, viewModel:
    MapViewModel = hiltViewModel()
) {

    recetaJson?.let {
        viewModel.setReceta(recetaJson)
        viewModel.updateMarkerLatLng(viewModel.receta.location)
    }


    Scaffold (
        topBar = {
            MapTop(navHost)
        },
        content = {
            MapContent()
        },
        bottomBar = {}
    )
}