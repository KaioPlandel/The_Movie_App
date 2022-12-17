package com.plandel.themovieapp.screens.main_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plandel.themovieapp.model.ApiResponse
import com.plandel.themovieapp.model.Movie
import com.plandel.themovieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private var _moviesA = MutableStateFlow(emptyList<Movie>())
    val moviesA: StateFlow<List<Movie>> get() = _moviesA

    private var _moviesTop = MutableStateFlow(emptyList<Movie>())
    val moviesTop: StateFlow<List<Movie>> get() = _moviesTop

    private var _moviesFavorite= MutableStateFlow(emptyList<Movie>())
    val moviesFavorite: StateFlow<List<Movie>> get() = _moviesFavorite

    private var _moviesSearch= MutableStateFlow(emptyList<Movie>())
    val moviesSearch: StateFlow<List<Movie>> get() = _moviesSearch

    init {
        getLastMovies()
    }

    fun getLastMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getMovies()
                _moviesA.value = response.movies
                _moviesTop.value = response.movies
                _moviesFavorite.value = response.movies

            } catch (e: Exception) {

            }
        }
    }
    fun getTopMovies(session: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies()
                _moviesTop.value = response.movies

            } catch (e: Exception) {

            }
        }
    }
    fun getFavoriteMovies(session: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovies()
                _moviesFavorite.value = response.movies

            } catch (e: Exception) {

            }
        }
    }

    fun searchMovie(name: String?){
        viewModelScope.launch {
            try {
                var  response = repository.searchMovie(name)
                _moviesSearch.value = response.movies
            }catch (e: Exception){

            }
        }
    }

}