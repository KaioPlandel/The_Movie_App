package com.plandel.themovieapp.repository

import com.plandel.themovieapp.api.MovieApi
import com.plandel.themovieapp.model.ApiResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    suspend fun getMovies(session: String): ApiResponse = movieApi.getMovies(session)

    suspend fun getMovieById(id: Int): ApiResponse = movieApi.getMovieById(id)

    suspend fun searchMovie(name: String?): ApiResponse = movieApi.searchMovie(name)
}