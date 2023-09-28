package com.ccastro.cooking.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ccastro.cooking.R


val InterFamily: FontFamily = FontFamily(
    Font(R.font.inter_black, FontWeight.Black),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extra_bold, FontWeight.ExtraBold),
    Font(R.font.inter_extra_light, FontWeight.ExtraLight),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_thin, FontWeight.Thin)
)

// Set of Material typography styles to start with
val defaultTypography = Typography()
val Typography = Typography(

    displayLarge = defaultTypography.displayLarge.copy(fontFamily = InterFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = InterFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = InterFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = InterFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = InterFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = InterFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = InterFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = InterFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = InterFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = InterFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = InterFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = InterFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = InterFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = InterFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = InterFamily)


    /* Other default text styles to override

    bodyLarge = TextStyle (
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)