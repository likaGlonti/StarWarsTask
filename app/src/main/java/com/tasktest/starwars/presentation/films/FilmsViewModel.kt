package com.tasktest.starwars.presentation.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasktest.starwars.domain.usecase.film.GetFilmsByCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val getFilmsByCharacterUseCase: GetFilmsByCharacterUseCase
) : ViewModel() {

    private val _filmState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> get() = _filmState

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        viewModelScope.launch {
            getFilmsByCharacterUseCase(listOf(1, 2, 3)).collect {
                _filmState.emit(UiState.FilmData(it.map { film ->
                    FilmUI(
                        film.title,
                        film.releaseDate
                    )
                }))
            }
        }
    }
}

sealed interface UiState {
    object Loading : UiState
    data class FilmData(val data: List<FilmUI>) : UiState
}