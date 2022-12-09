package com.plandel.themovieapp.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.plandel.themovieapp.navigation.screens.MovieScreen
import com.plandel.themovieapp.screens.details_screen.DetailsScreen
import com.plandel.themovieapp.screens.main_screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = MovieScreen.MainScreen.route) {

        composable(MovieScreen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(MovieScreen.AllMoviesScreen.route) {

        }

        composable(MovieScreen.DetailsScreen.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                defaultValue = 1
            }
        )) { entry ->
            DetailsScreen(id = entry.arguments?.getInt("id"), navController = navController)
        }
    }
}