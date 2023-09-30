package com.ccastro.cooking.presentation.screens.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ccastro.cooking.presentation.components.TittleTerciaryText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTop(navHost: NavHostController, title: String) {
    TopAppBar(title = {
        Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "Icono atras")
        TittleTerciaryText(text = title)
    })
}