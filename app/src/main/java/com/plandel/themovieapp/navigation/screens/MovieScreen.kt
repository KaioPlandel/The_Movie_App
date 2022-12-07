package com.plandel.themovieapp.navigation.screens

sealed class MovieScreen(val route: String) {
    object MainScreen : MovieScreen("main_screen")
    object DetailsScreen : MovieScreen("details_screen/{movieId}") {
        fun sendMovieId(id: Int): String {
            return "details_screen/$id"
        }
    }

    object AllMoviesScreen : MovieScreen("all_movie")
}
