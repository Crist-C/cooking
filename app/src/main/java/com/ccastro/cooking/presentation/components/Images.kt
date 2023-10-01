package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun AsyncImage(url: String, modifier: Modifier = Modifier, imgShapes: RoundedCornerShape = RoundedCornerShape(10.dp)) {

    SubcomposeAsyncImage(
        model = url,
        loading = {
            CircularProgressIndicator(modifier = Modifier
                .align(alignment = Alignment.Center)
                .align(Alignment.Center)
                .align(Alignment.Center)
                .align(Alignment.Center)
                .padding(100.dp))
        },
        alignment = Alignment.Center,
        modifier = modifier
            .clip(imgShapes)
            .border(1.dp, color = Color.LightGray)
            .size(100.dp),
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
fun IconImageClicked(iconResource: Any, modifier: Modifier = Modifier,
                     color: Color = Color.Gray, clickFunction: () -> Unit) {

    val indication = rememberRipple(color = color) // Cambia el color aquí
    CompositionLocalProvider(LocalIndication provides indication) {
        Surface(modifier = Modifier.clickable(onClick = clickFunction),
            content = {
                Box(modifier = modifier, contentAlignment = Alignment.Center) {
                    Divider(modifier = Modifier
                        .fillMaxSize()
                        .clickable { clickFunction.invoke() },
                        color = Color.Transparent)
                    Image(
                        imageVector = iconResource as ImageVector,
                        modifier = modifier
                            .wrapContentSize(align = Alignment.Center)
                            .padding(0.dp),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "like icon",
                        colorFilter = ColorFilter.tint(Blue80)
                    )
                }
            }
        )
    }
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
                AsyncImage( url = images["Left"]?: "", modifier = Modifier.fillMaxWidth(0.3f))
            }
            Row(Modifier.shadow(5.dp)) {
                AsyncImage( url = images["Rigth"]?: "", modifier = Modifier.fillMaxWidth(0.4f))
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
                .fillMaxWidth(0.5f)
                .size(120.dp))

        }

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

@Preview
@Composable
fun ImageIconClickedPreview(){
    CookingTheme {
        IconImageClicked(Icons.Rounded.Favorite){}
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
