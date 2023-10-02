package com.ccastro.cooking.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navHost: NavHostController) {

    Scaffold(
        topBar = {
            HomeScreenTopBar(navHost)
        },
        content = {

        },
        bottomBar = {

        }
    )
}