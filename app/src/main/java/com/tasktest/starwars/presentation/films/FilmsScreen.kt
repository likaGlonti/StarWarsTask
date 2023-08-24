package com.tasktest.starwars.presentation.films

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    LazyColumn(modifier.fillMaxSize()) {

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
            modifier = modifier.paddingFromBaseline(top = 37.dp),
            text = film.title, style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = modifier.paddingFromBaseline(bottom = 42.dp),
            text = stringResource(R.string.released, film.releaseDate),
            style = MaterialTheme.typography.headlineSmall
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