package com.tasktest.starwars.presentation.films

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tasktest.starwars.R
import com.tasktest.starwars.ui.theme.StarWarsTheme

@Composable
fun FilmsScreen(
    filmIds: List<Int>,
    modifier: Modifier = Modifier,
    filmsViewModel: FilmsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { filmsViewModel.fetchFilms(filmIds) }
    val state = filmsViewModel.state.collectAsState()
    when (state.value) {
        is UiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)

            )
        }

        is UiState.FilmData -> {
            LazyColumn(modifier.fillMaxSize()) {
                items(items = (state.value as UiState.FilmData).data) {
                    FilmItem(film = it)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun FilmItem(modifier: Modifier = Modifier, film: FilmUI) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Text(
            modifier = modifier.paddingFromBaseline(top = 42.dp),
            text = film.title, style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = modifier.paddingFromBaseline(bottom = 42.dp),
            text = stringResource(R.string.released, film.releaseDate),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun FilmItemPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            FilmItem(film = FilmUI("Revenge of the Sith", "May 19, 2005"))
        }
    }
}