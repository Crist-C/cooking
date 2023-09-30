package com.ccastro.cooking.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ccastro.cooking.presentation.screens.details.DetailsScreen
import com.ccastro.cooking.presentation.screens.home.HomeScreen
import com.ccastro.cooking.presentation.screens.map.MapScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = AppScreens.home.route
    ) {
        composable(route = AppScreens.home.route) {
            HomeScreen(navHostController)
        }
        composable(AppScreens.detail.route+"/{id}", arguments = listOf(navArgument(name = "id") {
            type = NavType.StringType
        }) ) {
            DetailsScreen(navHostController, id = it.arguments?.getString("id"))
        }
        composable(AppScreens.map.route) {
            MapScreen(navHostController)
        }
    }
}