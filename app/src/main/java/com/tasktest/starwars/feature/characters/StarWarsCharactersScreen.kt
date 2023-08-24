package com.tasktest.starwars.feature.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tasktest.starwars.CharacterUI

@Composable
fun StarWarsCharactersScreen(
    modifier: Modifier = Modifier,
    charactersVM: StarWarsCharactersVM = hiltViewModel()
) {
    val charactersPagingItems: LazyPagingItems<CharacterUI> =
        charactersVM.state.collectAsLazyPagingItems()
    Box(modifier) {
        StarWarsCharactersList(charactersPagingItems,  {}, {}, modifier)
    }
}