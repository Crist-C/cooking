package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.domain.models.Location
import com.ccastro.cooking.presentation.ui.theme.CookingTheme
import com.ccastro.cooking.presentation.ui.theme.Green200

@Composable
fun LocationInformation(location: Location, arrangement: Arrangement.Horizontal, modifier: Modifier = Modifier) {

    Row( modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    ) {
        contentColorFor(backgroundColor = Color.Blue)
        AsyncImage( url = location.urlImgBandera,
            Modifier
                .size(24.dp, 20.dp),
        )
        InformativeText("${location.pais}, ${location.regionName}", Modifier.padding(horizontal = 4.dp))
        Icon(imageVector = Icons.Rounded.Place, contentDescription = "Icono ubicación", tint = Green200,
            modifier = Modifier.clickable {  }
        )
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LocationInformationPreview() {
    CookingTheme {
        LocationInformation(location = Location(
            urlImgBandera = "https://media.istockphoto.com/id/967321126/es/vector/vector-de-bandera-del-per%C3%BA-proporci%C3%B3n-2-3-bandera-bicolor-nacional-peruana.jpg?s=1024x1024&w=is&k=20&c=MX21eYwejH9ob5FAAg9uU9Zj_U9qxoZVKAgzHHQM7ro=",
            pais =  "Perú",
            lat = -12.046374f,
            long = -77.04279f,
            regionName =  "Lima"
        ),
            Arrangement.Start
        )
    }
}