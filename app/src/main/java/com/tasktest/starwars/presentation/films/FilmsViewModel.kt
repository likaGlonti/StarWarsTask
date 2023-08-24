package com.tasktest.starwars.presentation.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasktest.starwars.domain.usecase.film.GetFilmsByCharacterUseCase
import com.tasktest.starwars.domain.utils.toFormattedDate
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

    fun fetchFilms(filmIds: List<Int>) {
        viewModelScope.launch {
            getFilmsByCharacterUseCase(filmIds).collect {
                _filmState.emit(UiState.FilmData(it.map { film ->
                    FilmUI(
                        film.title,
                        film.releaseDate.toFormattedDate()
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