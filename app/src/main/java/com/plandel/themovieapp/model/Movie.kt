package com.plandel.themovieapp.model


@kotlinx.serialization.Serializable
data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val categories: List<String>,
    val rating: Double,
    val year: Int,
)
