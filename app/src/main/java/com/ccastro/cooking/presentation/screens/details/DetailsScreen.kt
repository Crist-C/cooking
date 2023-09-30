package com.ccastro.cooking.presentation.screens.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navHost: NavHostController, id: String?, viewModel: DetalisViewModel = hiltViewModel()) {

    //val receta = viewModel.cargarReceta()

    Toast.makeText(LocalContext.current, "ID: $id", Toast.LENGTH_SHORT).show()

    Scaffold(
        topBar = {
            DetailsTop(navHost = navHost, title = "Aji de Gallina")
        },
        content = {
            DetailsContent(navHost = navHost)
        },
        bottomBar = {}
    )
}