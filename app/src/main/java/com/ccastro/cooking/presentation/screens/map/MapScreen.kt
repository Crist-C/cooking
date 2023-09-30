package com.ccastro.cooking.presentation.screens.map

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navHostController: NavHostController) {
    Scaffold (
        topBar = {},
        content = {
                  MapContent(navHostController)
        },
        bottomBar = {}
    )
}