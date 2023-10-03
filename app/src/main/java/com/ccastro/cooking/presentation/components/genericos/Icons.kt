package com.ccastro.cooking.presentation.components.genericos

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ccastro.cooking.presentation.navigation.AppScreens
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.CookingTheme


@Composable
fun IconImageClicked(iconResource: Any, modifier: Modifier = Modifier,
                     color: Color = Blue80, clickFunction: () -> Unit) {

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
fun IconBackArrow(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier
            .padding(horizontal = 16.dp)) {
        IconImageClicked(Icons.Outlined.ArrowBack,
            Modifier
                .size(50.dp)
                .clip(CircleShape)){
            onClick()
        }
    }
}

// ---------------------- PREVIEWS -------------------------- //
@Preview
@Composable
fun ImageIconClickedPreview(){
    CookingTheme {
        IconImageClicked(Icons.Rounded.Favorite){}
    }
}