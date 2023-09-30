package com.ccastro.cooking.presentation.navigation

sealed class AppScreens(val route: String) {
    object home: AppScreens("Home")
    object detail: AppScreens("detail")
    object map: AppScreens("map")
}