package com.ccastro.cooking.presentation.navigation

import android.net.Uri
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
        startDestination = AppScreens.Home.route
    ) {

        composable(route = AppScreens.Home.route) {
            HomeScreen(navHostController)
        }

        composable(AppScreens.Detail.route+"/{${NavArguments.Receta.key}}",
            arguments = listOf(navArgument(name = NavArguments.Receta.key) {
            type = NavType.StringType
        }) ) {
            it.arguments?.getString(NavArguments.Receta.key).let { locationString ->
                DetailsScreen(navHostController, recetaJson = Uri.decode(locationString))
            }
        }

        composable(AppScreens.Map.route+"/{${NavArguments.Receta.key}}",
            arguments = listOf(navArgument(name = NavArguments.Receta.key) {
            type = NavType.StringType
        })) {
            it.arguments?.getString(NavArguments.Receta.key).let { recetaJson ->
                MapScreen(navHostController, recetaJson = Uri.decode(recetaJson))
            }
        }
    }
}

sealed class NavArguments(val key: String) {
    object Receta: NavArguments("receta")
}