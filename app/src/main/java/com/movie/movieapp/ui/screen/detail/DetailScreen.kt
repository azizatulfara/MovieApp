package com.movie.movieapp.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.movie.movieapp.core.model.MovieData
import com.movie.movieapp.core.model.movieList

@Composable
fun DetailScreen(
    title: String,
    viewModel: DetailViewModel = viewModel(),
) {
    viewModel.movieState.collectAsStateWithLifecycle().value.let {
        if (it.title.isNullOrEmpty()) {
            viewModel.getMovie(title)
        }

        DetailContent(it)
    }
}

@Composable
fun DetailContent(
    movie: MovieData,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Surface(
            color = Color.LightGray,
            modifier = Modifier
                .padding(24.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.6f)
                            .clip(RoundedCornerShape(15.dp))
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = movie.fullTitle ?: "Null",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "star_icon", tint = Color.Yellow)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = movie.imDbRating ?: "0.0",
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "star_icon", tint = Color.Black)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = movie.year ?: "0000",
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "star_icon", tint = Color.Red)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = movie.imDbRatingCount ?: "0000",
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailPreview() {
    DetailContent(movie = movieList.first())
}