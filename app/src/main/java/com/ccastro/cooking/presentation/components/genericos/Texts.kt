package com.ccastro.cooking.presentation.components.genericos

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun TittleText(text: String, modifier: Modifier = Modifier, align: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontStyle = FontStyle.Normal,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = modifier,
        textAlign = align,
        lineHeight = 24.sp
    )

}

@Composable
fun TittleSecondaryText(text: String, modifier: Modifier = Modifier, align: TextAlign = TextAlign.Start) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier,
        textAlign = align
    )
}

@Composable
fun TittleSecondaryBoldText(text: String, modifier: Modifier = Modifier, align: TextAlign = TextAlign.Start) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier,
        textAlign = align
    )
}

@Composable
fun TittleTerciaryText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}

@Composable
fun InformativeText(text: String, modifier: Modifier = Modifier, align: TextAlign = TextAlign.Start) {
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier,
        textAlign = align
    )
}

@Composable
fun ContentText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ParagraphText(text: String, modifier: Modifier = Modifier, maxLines: Int = 3) {

    var showAllTextLines by remember {
        mutableStateOf(false)
    }

    Text(
        text = text,
        maxLines = if (showAllTextLines) Int.MAX_VALUE else maxLines,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodySmall,
        fontSize = 13.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ))
            .clickable { showAllTextLines = !showAllTextLines }
    )

}

@Composable
fun DetailText(text: String, modifier: Modifier = Modifier, maxLines: Int = 3) {

    Text(
        text = text,
        maxLines = maxLines,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodySmall,
        fontSize = 13.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .fillMaxWidth()
    )

}


// ---------- Previews ------------ //

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TiitleTextPreview() {
    CookingTheme {
        Column {
            TittleText(text = "Sancocho")
            InformativeText(text = "Perú, Arequipa")
            TittleSecondaryText(text = "Ingredientes", align = TextAlign.Center)
            TittleTerciaryText("Descripción")
            ParagraphText(text = "Cocinar a fuego lento por 10 minutos.")
            ContentText(text = "1. Debes pelar y lavar todos los ingredientes para que despues de eso puedas ponerlos en la estufa")
        }
    }
}