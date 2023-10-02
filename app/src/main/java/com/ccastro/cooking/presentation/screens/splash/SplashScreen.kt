package com.ccastro.cooking.presentation.screens.splash

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen() {
    Scaffold(
        topBar = {},
        content = {
                  SplashContent()
        },
        bottomBar = {}
    )
}