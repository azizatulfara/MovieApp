package com.movie.movieapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.movieapp.core.model.FakeDataSource
import com.movie.movieapp.core.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _movieList: MutableStateFlow<List<MovieData>> = MutableStateFlow(emptyList())
    val movieList: StateFlow<List<MovieData>> get() = _movieList

    fun getAllMovies() {
        viewModelScope.launch {
            _movieList.value = FakeDataSource.getData()
        }
    }
}