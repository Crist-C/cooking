package com.ccastro.cooking.presentation.screens.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.cooking.core.Constants.TAG

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navHost: NavHostController,
    recetaJson: String?, viewModel:
    MapViewModel = hiltViewModel()
) {

    recetaJson?.let {
        Log.i(TAG, "receta En Mapa: $recetaJson")
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