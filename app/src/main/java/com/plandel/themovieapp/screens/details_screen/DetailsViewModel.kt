package com.plandel.themovieapp.screens.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plandel.themovieapp.model.Movie
import com.plandel.themovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _movie = MutableStateFlow(emptyList<Movie>())
    val movie: StateFlow<List<Movie>> get() = _movie

init {
    getMovieById(1)
}
    fun getMovieById(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieById(id)
                if (response.success) {
                    _movie.value = response.movies
                }

            } catch (e: Exception) {

            }
        }
    }
}