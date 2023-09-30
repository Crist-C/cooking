package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ccastro.cooking.presentation.ui.theme.CookingTheme
import com.ccastro.cooking.presentation.ui.theme.White200

@Composable
fun TittleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontStyle = FontStyle.Normal,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = modifier
    )

}

@Composable
fun TittleSecondaryText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
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
fun InformativeText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.displaySmall,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}



@Composable
fun ParagraphText(text: String, modifier: Modifier = Modifier, maxLines: Int = 3) {
    Text(
        text = text,
        maxLines = maxLines,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.fillMaxWidth()
    )

}

@Composable
fun TextOnButton(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = White200,
        modifier = modifier
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TiitleTextPreview() {
    CookingTheme {
        Column {
            TittleText(text = "Sancocho")
            InformativeText(text = "Perú, Arequipa")
            TittleSecondaryText(text = "Ingredientes")
            TittleTerciaryText("Descripción")
            ParagraphText(text = "Cocinar a fuego lento por 10 minutos.")
        }
    }
}