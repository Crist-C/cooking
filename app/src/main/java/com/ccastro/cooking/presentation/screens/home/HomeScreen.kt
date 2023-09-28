package com.ccastro.cooking.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {},
        content = {
                  HomeScreenContent()
        },
        bottomBar = {
            BottomAppBar {

            }
        }
    )
}