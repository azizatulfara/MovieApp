package com.movie.movieapp.ui.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Grid: Screen("grid")
    data object About: Screen("about")
    data object DetailPage: Screen("home/{title}") {
        fun createRoute(title: String) = "home/$title"
    }
}