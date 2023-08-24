package com.tasktest.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tasktest.starwars.presentation.characters.StarWarsCharactersScreen
import com.tasktest.starwars.presentation.films.FilmsScreen

@Composable
fun StarWarsNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = AppScreen.CharactersScreen.route,
    ) {

        composable(route = AppScreen.CharactersScreen.route) {
            StarWarsCharactersScreen()
        }

        composable(
            route = AppScreen.FilmsScreen.route
        ) {
            FilmsScreen()
        }
    }
}

const val FILM_IDS_ARG = "FILM_IDS_ARG"