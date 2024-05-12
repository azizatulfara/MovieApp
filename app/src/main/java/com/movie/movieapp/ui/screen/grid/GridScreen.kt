package com.movie.movieapp.ui.screen.grid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.movie.movieapp.core.model.MovieData
import com.movie.movieapp.core.model.movieList
import com.movie.movieapp.ui.components.MovieItemRow
import com.movie.movieapp.ui.screen.home.HomeViewModel

@Composable
fun GridScreen(
    viewModel: HomeViewModel = viewModel(),
    navigateToDetail: (String) -> Unit,
) {
    viewModel.movieList.collectAsStateWithLifecycle().value.let {
        if (it.isEmpty()) {
            viewModel.getAllMovies()
        }

        GridContent(it, navigateToDetail)
    }
}

@Composable
fun GridContent(
    movies: List<MovieData>,
    navigateToDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        items(movies, key = { it.id ?: "" }) {
            MovieItemRow(
                image = it.image,
                title = it.title ?: "",
                rating = it.imDbRating ?: "0.0",
                release = it.year ?: "0000",
                modifier = Modifier.clickable {
                    it.title?.let(navigateToDetail)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GridPreview() {
    GridContent(movies = movieList) {

    }
}