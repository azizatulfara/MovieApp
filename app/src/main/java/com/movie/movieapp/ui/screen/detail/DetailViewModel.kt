package com.movie.movieapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.movieapp.core.model.FakeDataSource
import com.movie.movieapp.core.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    private val _movieState: MutableStateFlow<MovieData> = MutableStateFlow(MovieData())
    val movieState: StateFlow<MovieData> get() = _movieState

    fun getMovie(title: String) {
        viewModelScope.launch {
            _movieState.value = FakeDataSource.getData().first {
                it.title == title
            }
        }
    }
}