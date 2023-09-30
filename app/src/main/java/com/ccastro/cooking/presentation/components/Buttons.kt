package com.ccastro.cooking.presentation.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    icon: ImageVector? = null,//Icons.Default.ArrowForward
    shape: CornerBasedShape = ShapeDefaults.ExtraLarge,
    enable: Boolean = true
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = { onClick() },
        colors = colors,
        shape = shape,
        enabled = enable
    ){
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.padding(2.dp),
            text = text,
            //style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickableCustomColor(
    color: Color = Blue80,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val indication = rememberRipple(color = color) // Cambia el color aquí
    CompositionLocalProvider(LocalIndication provides indication) {
        Surface(modifier = Modifier.clickable(onClick = onClick), content = content)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultButtonPreview(){
    CookingTheme() {
        DefaultButton(text = "AGREGAR TARJETA", onClick = {})
    }
}