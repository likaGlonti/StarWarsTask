package com.tasktest.starwars.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun StarWarsCharactersScreen(
    modifier: Modifier = Modifier,
    charactersVM: StarWarsCharactersViewModel = hiltViewModel()
) {
    val charactersPagingItems: LazyPagingItems<CharacterUI> =
        charactersVM.state.collectAsLazyPagingItems()
    Box(modifier) {
        StarWarsCharactersList(charactersPagingItems, onRetry = { TODO() }, modifier)
    }
}