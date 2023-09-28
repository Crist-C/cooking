package com.ccastro.cooking.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ccastro.cooking.R
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun RecetaOnListComponent(receta: Receta) {

    Surface (
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 18.dp),
        shape = MaterialTheme.shapes.large,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(0.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = receta.nombre,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.displayMedium,
                softWrap = false,
                fontSize = 28.sp,
                modifier = Modifier.padding(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                BoxWithConstraints(contentAlignment = Alignment.Center) {

                    SubcomposeAsyncImage(
                        model = receta.imagenes[0],
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = stringResource(R.string.imagen_receta_descripcion)
                    )

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(receta.imagenes[0])
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder_2),
                        contentDescription = stringResource(R.string.imagen_receta_descripcion),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(150.dp)
                            .heightIn(150.dp, 300.dp)
                    )
                }

            }

            Text(
                text = receta.preparacion,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodySmall,
                softWrap = false,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RecetaOnListPreview() {
    CookingTheme {
        RecetaOnListComponent(receta =
            Receta(
                nombre = "Sancocho",
                imagenes = listOf("https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp", "Url2", "Url3"),
                location = Location("Colombia", 7.1f,12.4f),
                ingredientes = listOf(Ingredientes("Papa", "1 libra")),
                preparacion = "Cocinar a fuego lento"
            )
        )
    }
}