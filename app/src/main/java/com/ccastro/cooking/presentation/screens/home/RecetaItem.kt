package com.ccastro.cooking.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Rounded
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ccastro.cooking.R
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.AsyncImage
import com.ccastro.cooking.presentation.components.ClickableCustomColor
import com.ccastro.cooking.presentation.components.ImageIconClicked
import com.ccastro.cooking.presentation.components.InformativeText
import com.ccastro.cooking.presentation.components.ParagraphText
import com.ccastro.cooking.presentation.components.TextOnButton
import com.ccastro.cooking.presentation.components.TittleTerciaryText
import com.ccastro.cooking.presentation.components.TittleText
import com.ccastro.cooking.presentation.navigation.AppScreens
import com.ccastro.cooking.presentation.ui.theme.Blue200
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun RecetaItem(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    val imagesMap = listOf("Left", "Center", "Rigth").zip(receta.imagenes).toMap()

    Surface (
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 16.dp, top = 18.dp, bottom = 16.dp, end = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            
            RecetaLayoutTop(receta = receta)

            //AsyncImage(url = receta.imagenes[0])
            ImagesLayout(imagesMap)

            RecetaLayoutBottom(receta = receta, navHost = navHost)
        }
    }
}

@Composable
fun RecetaLayoutTop(receta: Receta, modifier: Modifier = Modifier) {

    val favoriteState = remember {
        mutableStateOf(true)
    }

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

            TittleText(text = receta.nombre)

            ImageIconClicked(
                iconResource = if(favoriteState.value) Rounded.FavoriteBorder else Rounded.Favorite,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape), color = Blue200
            ) {
                favoriteState.value = !favoriteState.value
            }

        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage( url = receta.location.urlImgBandera,
                Modifier
                    .size(32.dp, 24.dp)
                    .padding(horizontal = 4.dp), RoundedCornerShape(0.dp) )
            InformativeText("${receta.location.pais}, ${receta.location.regionName}")
        }

    }
}

@Composable
fun ImagesLayout(images: Map<String, String>) {

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(0.25f)
                .shadow(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage( url = images["Left"]?: "")

            Divider(Modifier.size(80.dp))

            AsyncImage( url = images["Rigth"]?: "")
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .shadow(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage( url = images["Center"]?: "")

        }
    }
}

@Composable
fun RecetaLayoutBottom(receta: Receta, modifier: Modifier = Modifier, navHost: NavHostController) {

    val showAllTextLines = remember {
        mutableStateOf(false)
    }

    Column(modifier =  modifier) {

        TittleTerciaryText(text = "Descripción")

        ClickableCustomColor(onClick = {showAllTextLines.value = !showAllTextLines.value}) {
            ParagraphText(receta.descripcion, maxLines = if (showAllTextLines.value) Int.MAX_VALUE else 3)
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(modifier= Modifier.padding(top = 4.dp),onClick = { navHost.navigate(AppScreens.detail.route+"/${receta.id}")}) {
                Icon(imageVector = Rounded.PlayArrow, contentDescription = stringResource(R.string.ir_a_prepararlo))
                TextOnButton(text = stringResource(R.string.deseo_prepararlo))
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
                location = Location("https://www.flaticon.es/icono-gratis/peru_5344559","Perú",7.1f,12.4f, "Arequipa"),
                ingredientes = listOf(Ingredientes("Papa", "1 libra")),
                preparacion = "Cocinar a fuego lento",
                descripcion = "El \"Sancocho\" es una deliciosa preparación peruana que consiste en una papa cocida rellena de una mezcla sazonada de carne molida, cebolla, ajo, aceitunas, huevo duro y especias. La papa rellena se empaniza y se fríe hasta que esté dorada y crujiente por fuera. Es un platillo reconfortante y sabroso."
            ),
            navHost = rememberNavController()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImagesLayoutPreview() {
    val imagesMap = listOf("Left", "Center", "Rigth").zip(listOf("","","")).toMap()
    CookingTheme {
        ImagesLayout(imagesMap)
    }
}
