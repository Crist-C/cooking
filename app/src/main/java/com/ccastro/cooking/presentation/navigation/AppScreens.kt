package com.ccastro.cooking.presentation.navigation

import android.net.Uri

sealed class AppScreens(val route: String) {
    object Home: AppScreens("home")
    object Detail: AppScreens("detail") {
        fun passRecetaArgument(receta: String) = "detail/${Uri.encode(receta)}"
    }
    object Map: AppScreens("map") {
        fun passRecetaArgument(receta: String) = "map/${Uri.encode(receta)}"
    }
}