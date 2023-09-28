package com.ccastro.cooking.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Green200,
    onPrimary = GreenVariant40,
    primaryContainer = Green500,
    onPrimaryContainer = Green40,
    inversePrimary = Blue200, // Pendiente variar

    secondary = Blue200,
    onSecondary = Blue40,
    secondaryContainer = BlueVariant700,
    onSecondaryContainer = Blue80,

    tertiary = Orange200,
    onTertiary = Orange40,
    tertiaryContainer = Orange700,
    onTertiaryContainer = Orange80,

    background = Gray700,
    onBackground = White200,
    surface = Gray500,
    onSurface = White200,
    surfaceVariant = Gray80,
    onSurfaceVariant = White200,
    surfaceTint = Green500,

    error = Red500,
    errorContainer = Red700,

    outline = Blue200,
    outlineVariant = Blue200,

    scrim = Gray700
)

private val LightColorScheme = lightColorScheme(

    primary = Green200,
    onPrimary = GreenVariant40,
    primaryContainer = Green200,      // TopBar Color
    onPrimaryContainer = Green500,      // Text on TopBar Color
    inversePrimary = Red200,            // Pendiente variar

    secondary = Blue200,
    onSecondary = Blue40,
    secondaryContainer = Green500,          // Filled Button Color
    onSecondaryContainer = GreenVariant40,  // Text on Filled Button Color

    tertiary = Orange200,
    onTertiary = Orange40,
    tertiaryContainer = Orange700,
    onTertiaryContainer = Orange80,

    background = Color.White,
    onBackground = Gray500,

    surface = GreenVariant40,         // elevatedButtons, Superficies, Cards,
    onSurface = Gray700,        // Color de Letra
    surfaceVariant = White200,
    onSurfaceVariant = BlueVariant700,
    surfaceTint = Blue80,

    error = Red500,
    errorContainer = Red700,

    outline = Gray80,
    outlineVariant = Blue200,

    scrim = Gray700,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CookingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}