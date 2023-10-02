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
        composable(AppScreens.Detail.route+"/{${NavArguments.RecetaId.key}}",
            arguments = listOf(navArgument(name = NavArguments.RecetaId.key) {
            type = NavType.StringType
        }) ) {
            DetailsScreen(navHostController, id = it.arguments?.getString(NavArguments.RecetaId.key))
        }
        composable(AppScreens.Map.route+"/{${NavArguments.Location.key}}",
            arguments = listOf(navArgument(name = NavArguments.Location.key) {
            type = NavType.StringType
        })) {it->
            it.arguments?.getString(NavArguments.Location.key).let { locationString->
                MapScreen(navHostController, location = Uri.decode(locationString))
            }
        }
    }
}

sealed class NavArguments(val key: String) {
    object RecetaId: NavArguments("id")
    object Location: NavArguments("location")
}