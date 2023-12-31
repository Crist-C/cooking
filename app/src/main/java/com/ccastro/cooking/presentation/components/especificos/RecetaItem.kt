package com.ccastro.cooking.presentation.components.especificos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.R
import com.ccastro.cooking.core.Constants.gson
import com.ccastro.cooking.domain.models.Ingrediente
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.genericos.ClickableCustomColor
import com.ccastro.cooking.presentation.components.genericos.CustomButton
import com.ccastro.cooking.presentation.components.genericos.IconImageClicked
import com.ccastro.cooking.presentation.components.genericos.ImagesPresentation
import com.ccastro.cooking.presentation.components.genericos.ParagraphText
import com.ccastro.cooking.presentation.components.genericos.TittleTerciaryText
import com.ccastro.cooking.presentation.components.genericos.TittleText
import com.ccastro.cooking.presentation.navigation.AppScreens
import com.ccastro.cooking.presentation.screens.home.HomeViewModel
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun RecetaItem(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    val imagesMap = listOf("Left", "Center", "Rigth").zip(receta.imagenes).toMap()

    Surface (
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            
            RecetaLayoutTop(receta = receta, navHost = navHost)

            ImagesPresentation(imagesMap)

            RecetaLayoutBottom(receta = receta, navHost = navHost)
        }
    }
}

@Composable
fun RecetaLayoutTop(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    var favoriteState by remember {
        mutableStateOf(false)
    }
    favoriteState = receta.favorito

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TittleText(text = receta.nombre,align = TextAlign.Start,modifier = Modifier.weight(0.9f))

            IconImageClicked(
                iconResource = if(favoriteState) Rounded.Favorite else Rounded.FavoriteBorder,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .weight(1f)
            ) {
                favoriteState = !favoriteState
                viewModel.actualizarFavorito(receta, favoriteState)
            }

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            LocationInformation(receta, Arrangement.Start, navHost = navHost)
        }

    }
}

@Composable
fun RecetaLayoutBottom(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    Column(modifier =  modifier) {

        TittleTerciaryText(text = stringResource(R.string.descripci_n))

        Divider(thickness = 1.dp)

        ClickableCustomColor {
            ParagraphText(receta.descripcion, Modifier.padding(vertical = 4.dp), maxLines = 3)
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            CustomButton(resourcedText = R.string.deseo_prepararlo, icon = Rounded.PlayArrow) {
                navHost.navigate(AppScreens.Detail.passRecetaArgument(gson.toJson(receta)))
            }
        }
    }
}

/// PREVIEWS

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RecetaOnListPreview() {
    CookingTheme {

        RecetaItem(receta =
            Receta(
                nombre = "Sancocho",
                imagenes = listOf("https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp", "https://picsum.photos/id/10/500/700", "Url3"),
                location = Location("https://www.flaticon.es/icono-gratis/peru_5344559","Perú",7.1,12.4, "Arequipa"),
                ingredientes = listOf(Ingrediente("Papa", "1 libra")),
                preparacion = "Cocinar a fuego lento",
                descripcion = "El \"Sancocho\" es una deliciosa preparación peruana que consiste en una papa cocida rellena de una mezcla sazonada de carne molida, cebolla, ajo, aceitunas, huevo duro y especias. La papa rellena se empaniza y se fríe hasta que esté dorada y crujiente por fuera. Es un platillo reconfortante y sabroso."
            ),
            navHost = rememberNavController()
        )
    }
}

