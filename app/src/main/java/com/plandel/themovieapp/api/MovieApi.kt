package com.plandel.themovieapp.api

import com.plandel.themovieapp.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/movies")
    suspend fun getMovies(): ApiResponse

    @GET("/movie/v1")
    suspend fun getMovieById(
        @Query("id") session: Int
    ): ApiResponse

    @GET("/movies/v1/search")
    suspend fun searchMovie(
        @Query("name") name: String?
    ): ApiResponse
}