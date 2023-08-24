package com.tasktest.starwars.navigation

sealed class AppScreen(val route: String) {
    object CharactersScreen : AppScreen(CHARACTERS_SCREEN)
    object FilmsScreen : AppScreen(FILMS_SCREEN)
}

const val CHARACTERS_SCREEN = "CHARACTERS_SCREEN"
const val FILMS_SCREEN = "FILMS_SCREEN"
