package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ccastro.cooking.R
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun AsyncImage(url: String, modifier: Modifier = Modifier, imgShapes: RoundedCornerShape = RoundedCornerShape(10.dp)) {

    SubcomposeAsyncImage(
        model = url,
        loading = {
            CircularProgressIndicator(modifier = Modifier
                .align(alignment = Alignment.Center)
                .align(Alignment.Center)
                .align(Alignment.Center).align(Alignment.Center).padding(100.dp))
        },
        alignment = Alignment.Center,
        modifier = modifier
            .clip(imgShapes)
            .border(1.dp, color = Color.LightGray),
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
        modifier = Modifier
            .clip(CircleShape)
            .height(150.dp)
            .heightIn(150.dp, 300.dp)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AsyncImagePreview() {
    CookingTheme {
        AsyncImage(url = "https://cdn.colombia.com/gastronomia/2011/07/28/sancocho-de-cola-1650.webp")
    }
}