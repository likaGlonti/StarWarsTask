package com.tasktest.starwars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            route = AppScreen.FilmsScreen.route + ("/{$FILM_IDS_ARG}"),
            arguments = listOf(
                navArgument(FILM_IDS_ARG) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val filmIds =
                backStackEntry.arguments?.getString(FILM_IDS_ARG)?.split(",")?.map { it.toInt() }
            filmIds?.let { FilmsScreen(it.toList()) }
        }
    }
}

const val FILM_IDS_ARG = "FILM_IDS_ARG"