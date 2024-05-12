package com.movie.movieapp.core.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.movie.movieapp.R

@Parcelize
data class MovieData(
	val imDbRating: String? = null,
	val image: Int = R.drawable.ic_movie,
	val fullTitle: String? = null,
	val imDbRatingCount: String? = null,
	val year: String? = null,
	val rank: String? = null,
	val id: String? = null,
	val rankUpDown: String? = null,
	val title: String? = null,
	val crew: String? = null
) : Parcelable
