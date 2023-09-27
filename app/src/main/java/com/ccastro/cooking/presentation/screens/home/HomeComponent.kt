package com.ccastro.cooking.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun HomeScreenContent(viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(
            state.recetas.ifEmpty {
                listOf(
                    Receta(
                        nombre = "Sancocho",
                        imagenes = listOf("Url1", "Url2", "Url3"),
                        location = Location("Colombia", 7.1f,12.4f),
                        ingredientes = listOf(Ingredientes("Papa", "1 libra")),
                        preparacion = "Cocinar a fuego lento"
                    )
                )
            }
        ) {
            RecetaComponent(receta = it)
        }
    }
}

@Composable
fun RecetaComponent(receta: Receta) {

    Column {
        CampoComponent(nombreCampo = "ID: ", valor = receta.id.toString())
        CampoComponent(nombreCampo = "Nombre: ", valor = receta.nombre)
        CampoComponent(nombreCampo = "Imagenes: ", valor = receta.imagenes.toString())
        CampoComponent(nombreCampo = "Location: ", valor = receta.location.toString())
        CampoComponent(nombreCampo = "Ingredientes: ", valor = receta.ingredientes.toString())
        CampoComponent(nombreCampo = "Preparación: ", valor = receta.preparacion)
    }
}


@Composable
fun CampoComponent(nombreCampo: String, valor: String) {
    Row {
        Text(text = nombreCampo)
        Text(text = valor)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RecetaComponentPreview() {
    CookingTheme {
        RecetaComponent(
            receta = Receta(
                nombre = "Sancocho",
                imagenes = listOf("Url1", "Url2", "Url3"),
                location = Location("Colombia", 7.1f,12.4f),
                ingredientes = listOf(Ingredientes("Papa", "1 libra")),
                preparacion = "Cocinar a fuego lento"
            )
        )
    }
}