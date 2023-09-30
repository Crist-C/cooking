package com.ccastro.cooking.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.presentation.components.AsyncImage
import com.ccastro.cooking.presentation.components.ParagraphText
import com.ccastro.cooking.presentation.components.TittleSecondaryText
import com.ccastro.cooking.presentation.components.TittleText
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    viewModel: DetalisViewModel = hiltViewModel(),
    navHost: NavHostController
) {
    
    val state = viewModel.state
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(url = state.receta.imagenes[0], Modifier.fillMaxSize(0.25f))

        TittleText(text = state.receta.nombre)

        ParagraphText(text = state.receta.descripcion)

        TittleSecondaryText(text = "Ingredientes")

        state.receta.ingredientes.map {
            ParagraphText(text = "${it.nombre} ${it.cantidad}")
        }


        TittleSecondaryText(text = "Preparación")

        state.receta.preparacion.split("\\n").map {
            ParagraphText(text = it)
        }

    }
    
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailsContentPreview() {
    CookingTheme {
        DetailsContent(navHost = rememberNavController())
    }
}