package com.tasktest.starwars.feature.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tasktest.starwars.CharacterUI
import com.tasktest.starwars.domain.usecase.GetCharactersPagedDataUseCase
import com.tasktest.starwars.domain.usecase.GetMergedDataOfCharacterAndSpecieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarWarsCharactersVM @Inject constructor(
    private val getCharactersPagedDataUseCase: GetMergedDataOfCharacterAndSpecieUseCase
) : ViewModel() {

    private val _characterState: MutableStateFlow<PagingData<CharacterUI>> =
        MutableStateFlow(value = PagingData.empty())
    val state: MutableStateFlow<PagingData<CharacterUI>> get() = _characterState
    init {
        onEvent(CharacterListEvent.GetCharacters)
    }


    fun onEvent(event: CharacterListEvent) {
        viewModelScope.launch {
            when (event) {
                is CharacterListEvent.GetCharacters -> {
                    getMovies()
                }
            }
        }
    }

    private suspend fun getMovies() {
        getCharactersPagedDataUseCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _characterState.value = it
            }
    }

    sealed interface CharacterListEvent {
        data object GetCharacters : CharacterListEvent
    }
}