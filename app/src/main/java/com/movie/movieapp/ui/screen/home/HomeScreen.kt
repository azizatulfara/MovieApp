package com.movie.movieapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movie.movieapp.core.model.MovieData
import com.movie.movieapp.core.model.movieList
import com.movie.movieapp.ui.components.MovieItemRow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navigateToDetail: (String) -> Unit
) {
    viewModel.movieList.collectAsStateWithLifecycle().value.let {
        if (it.isEmpty()) {
            viewModel.getAllMovies()
        }

        HomeContent(it, navigateToDetail)
    }
}

@Composable
fun HomeContent(
    responseItems: List<MovieData>,
    navigateToDetail: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            fontSize = 20.sp,
            text = "Row"
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(responseItems.asReversed(), key = { it.id ?: "" }) {
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
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            fontSize = 20.sp,
            text = "Column"
        )
        LazyColumn (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(responseItems, key = { it.id ?: "" }) {
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
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeContent(responseItems = movieList) {

    }
}