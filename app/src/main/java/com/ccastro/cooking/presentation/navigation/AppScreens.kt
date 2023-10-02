package com.ccastro.cooking.presentation.navigation

import android.net.Uri

sealed class AppScreens(val route: String) {
    object Home: AppScreens("home")
    object Detail: AppScreens("detail")
    object Map: AppScreens("map") {
        fun passLocationArgument(location: String) = "map/${Uri.encode(location)}"
    }
}