package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ccastro.cooking.R
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun AsyncImage(url: String, modifier: Modifier = Modifier) {

    SubcomposeAsyncImage(
        model = url,
        loading = {
            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
        },
        alignment = Alignment.Center,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(R.string.imagen_receta_descripcion)
    )
}

@Composable
fun AsyncImageWithoutLoader(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder_white_2),
        contentDescription = stringResource(R.string.imagen_receta_descripcion),
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .clip(CircleShape)
            .height(150.dp)
            .heightIn(150.dp, 300.dp)
    )
}

@Composable
fun ImagesPresentation(images: Map<String, String>) {

    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(Modifier.shadow(5.dp)) {
                AsyncImage( url = images["Left"]?: "",
                    modifier = Modifier.fillMaxWidth(0.3f).size(100.dp).clip(RoundedCornerShape(10.dp)),
                )
            }
            Row(Modifier.shadow(5.dp)) {
                AsyncImage(
                    url = images["Rigth"]?: "",
                    modifier = Modifier.fillMaxWidth(0.43f).size(100.dp).clip(RoundedCornerShape(10.dp))
                )
            }

        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(0.4f)
                .shadow(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage( url = images["Center"]?: "", modifier = Modifier
                .fillMaxWidth(0.5f).size(120.dp).clip(RoundedCornerShape(10.dp)),
            )

        }

    }
}

@Composable
fun ImageCardResizable(urlImage: String, pagerOffset: Float, size: Int, modifier: Modifier = Modifier ) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(10.dp))
            .graphicsLayer {
                lerp( start = 0.55f, stop = 1f, fraction = 1f - pagerOffset.coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    start = 0.50f, // Transparencia
                    stop = 1f,
                    fraction = 1f - pagerOffset.coerceIn(
                        minimumValue = 0f,
                        maximumValue = 1f
                    )
                )
            },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        AsyncImage(url = urlImage, Modifier.size(size.dp))
    }
}

// ---------------------------------- PREVIEWS -------------------------------------------  //

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AsyncImagePreview() {
    CookingTheme {
        AsyncImage(url = "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp")
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImagesLayoutPreview() {
    val imagesMap = listOf("Left", "Center", "Rigth").zip(listOf("","","")).toMap()
    CookingTheme {
        ImagesPresentation(imagesMap)
    }
}
