package com.movie.movieapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieItemRow(
    image: Int,
    title: String,
    rating: String,
    release: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .height(350.dp)
            .width(400.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier,
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(
                modifier = modifier
                    .padding(start = 16.dp, end = 10.dp)
                    .fillMaxHeight(0.2f)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "star_icon", tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = rating,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "star_icon", tint = Color.Black)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = release,
                        fontWeight = FontWeight.Medium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}