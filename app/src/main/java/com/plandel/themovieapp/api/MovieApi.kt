package com.plandel.themovieapp.api

import com.plandel.themovieapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movies/v1")
    suspend fun getMovies(
        @Query("session") session: String
    ): ApiResponse

    @GET("/movie/v1")
    suspend fun getMovieById(
        @Query("id") session: Int
    ): ApiResponse
}