package com.ccastro.cooking.presentation.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccastro.cooking.presentation.components.genericos.AsyncImage
import com.ccastro.cooking.presentation.components.genericos.IconBackArrow
import com.ccastro.cooking.presentation.components.genericos.TittleTerciaryText
import com.ccastro.cooking.presentation.components.genericos.TittleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapTop(navHost: NavHostController, modifier: Modifier = Modifier, viewModel: MapViewModel = hiltViewModel()) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                TittleText(text = viewModel.receta.nombre)
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    TittleTerciaryText(text = "${viewModel.receta.location.regionName} - ")
                    TittleTerciaryText(text = viewModel.receta.location.pais)
                    AsyncImage( url = viewModel.receta.location.urlImgBandera,
                        Modifier
                            .size(24.dp, 20.dp).padding(horizontal = 4.dp),
                    )
                }
            }
        },
        navigationIcon = {
            IconBackArrow(modifier = Modifier.wrapContentSize()) {
                navHost.popBackStack()
            }
        }
    )
}