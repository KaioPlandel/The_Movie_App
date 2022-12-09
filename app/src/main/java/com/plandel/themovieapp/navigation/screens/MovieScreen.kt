package com.plandel.themovieapp.navigation.screens

sealed class MovieScreen(val route: String) {
    object MainScreen : MovieScreen("main_screen")
    object DetailsScreen : MovieScreen("details_screen")

    object AllMoviesScreen : MovieScreen("all_movie")

    fun withArgrs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }
}
