package com.ccastro.cooking.presentation.screens.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navHost: NavHostController, id: String?) {//, viewModel: DetalisViewModel = hiltViewModel()) {

    //val receta = viewModel.cargarReceta()

    Toast.makeText(LocalContext.current, "ID: $id", Toast.LENGTH_SHORT).show()

    Scaffold(
        topBar = {
            DetailsTop(navHost = navHost)
        },
        content = {
            DetailsContent(navHost = navHost)
        },
        bottomBar = {}
    )
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(rememberNavController(), "1")
}