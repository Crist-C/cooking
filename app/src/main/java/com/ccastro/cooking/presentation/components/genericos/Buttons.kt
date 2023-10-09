package com.ccastro.cooking.presentation.components.genericos

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ccastro.cooking.R
import com.ccastro.cooking.presentation.ui.theme.Blue80
import com.ccastro.cooking.presentation.ui.theme.CookingTheme

@Composable
fun CustomButton(
    resourcedText: Int,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    icon: ImageVector? = null,//Icons.Default.ArrowForward
    shape: CornerBasedShape = ShapeDefaults.ExtraLarge,
    enable: Boolean = true,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier.wrapContentSize(),
        onClick = { onClick() },
        colors = colors,
        shape = shape,
        enabled = enable
    ){
        if (icon != null) {
            Icon(imageVector = icon,contentDescription = "")
        }
        Text(
            text = stringResource(id = resourcedText),
            fontWeight = FontWeight.SemiBold
        )

    }

}

@Composable
fun ClickableCustomColor( color: Color = Blue80, onClick: () -> Unit = {}, content: @Composable () -> Unit) {
    val indication = rememberRipple(color = color)
    CompositionLocalProvider(LocalIndication provides indication) {
        Surface(modifier = Modifier.clickable(onClick = onClick), content = content)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultButtonPreview(){
    CookingTheme {
        CustomButton(resourcedText = R.string.imagen_receta_descripcion, onClick = {})
    }
}