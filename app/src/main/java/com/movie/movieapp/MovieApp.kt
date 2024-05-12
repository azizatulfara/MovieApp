package com.movie.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.movie.movieapp.ui.navigation.NavigationItem
import com.movie.movieapp.ui.navigation.Screen
import com.movie.movieapp.ui.screen.about.AboutScreen
import com.movie.movieapp.ui.screen.detail.DetailScreen
import com.movie.movieapp.ui.screen.grid.GridScreen
import com.movie.movieapp.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = { GetTitle(currentRoute) },
                navigationIcon = {
                    if (currentRoute == Screen.DetailPage.route) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (currentRoute != Screen.DetailPage.route) {
                BottomAppBar(navController)
            }
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailPage.createRoute(it))
                    }
                )
            }
            composable(Screen.Grid.route) {
                GridScreen {
                    navController.navigate(Screen.DetailPage.createRoute(it))
                }
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailPage.route,
                arguments = listOf(navArgument("title") { type = NavType.StringType })
            ) {
                val title = it.arguments?.getString("title") ?: ""
                DetailScreen(
                    title = title
                )
            }
        }
    }
}

@Composable
fun GetTitle(currentRoute: String?) {
    when (currentRoute) {
        Screen.Home.route -> Text("Home Screen")
        Screen.Grid.route -> Text("Grid Screen")
        Screen.About.route -> Text("About Screen")
        Screen.DetailPage.route -> Text("Detail Screen")
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    NavigationBar {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = "home_page"
            ),
            NavigationItem(
                title = stringResource(R.string.menu_grid),
                icon = Icons.Filled.Menu,
                screen = Screen.Grid,
                contentDescription = "grid_page"
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Filled.AccountCircle,
                screen = Screen.About,
                contentDescription = "about_page"
            )
        )

        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                },
                label = { Text(it.title) },
                selected = currentState == it.screen.route,
                onClick = {
                    navController.navigate(it.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}