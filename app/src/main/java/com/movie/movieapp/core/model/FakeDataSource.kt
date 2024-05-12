package com.movie.movieapp.core.model


object FakeDataSource {

    fun getData(): ArrayList<MovieData> {
        val movies = arrayListOf<MovieData>()
        movies.addAll(movieList)

        return movies
    }

}