package com.ccastro.cooking.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navHost: NavHostController, recetaJson: String?, viewModel: DetalisViewModel = hiltViewModel()) {

    recetaJson?.let {
        viewModel.setReceta(recetaJson)
    }

    Scaffold(
        topBar = {},
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