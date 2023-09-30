package com.ccastro.cooking.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    navHost: NavHostController
) {

    val state = viewModel.state

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(
            state.recetas.ifEmpty {
                listOf(
                    state.receta
                )
            }
        ) {
            RecetaItem(receta = it, navHost = navHost)
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    CookingTheme {
        HomeScreenContent(navHost = rememberNavController())
    }
}