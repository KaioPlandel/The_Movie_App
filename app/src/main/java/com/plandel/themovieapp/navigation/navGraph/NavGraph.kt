package com.plandel.themovieapp.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.plandel.themovieapp.navigation.screens.MovieScreen
import com.plandel.themovieapp.screens.main_screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = MovieScreen.MainScreen.route) {

        composable(MovieScreen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(MovieScreen.AllMoviesScreen.route) {

        }

        composable(MovieScreen.DetailsScreen.route, arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {

        }
    }
}