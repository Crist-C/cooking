package com.ccastro.cooking.presentation.components.especificos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ccastro.cooking.core.Constants.gson
import com.ccastro.cooking.domain.models.Receta
import com.ccastro.cooking.presentation.components.genericos.AsyncImage
import com.ccastro.cooking.presentation.components.genericos.InformativeText
import com.ccastro.cooking.presentation.navigation.AppScreens
import com.ccastro.cooking.presentation.ui.theme.Green200

@Composable
fun LocationInformation(
    receta: Receta, arrangement: Arrangement.Horizontal,
    navHost: NavHostController,
    modifier: Modifier = Modifier) {

    Surface(
        shape = CircleShape,
        shadowElevation = 3.dp,
        modifier = modifier
            .wrapContentSize()
            .clickable {
                navHost.navigate(AppScreens.Map.passRecetaArgument(gson.toJson(receta)))
            }
        ,
    ) {

        Row(
            Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = arrangement
        ) {
            AsyncImage( url = receta.location.urlImgBandera,
                Modifier
                    .size(24.dp, 20.dp),
            )
            InformativeText(
                "${receta.location.pais}, ${receta.location.regionName}",
                Modifier.padding(horizontal = 4.dp))
            Icon(imageVector = Icons.Rounded.Place,
                contentDescription = "Icono ubicación",
                tint = Green200
            )
        }
    }

}
