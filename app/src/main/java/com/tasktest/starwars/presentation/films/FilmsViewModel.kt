package com.tasktest.starwars.presentation.films

import androidx.lifecycle.ViewModel
import com.tasktest.starwars.domain.usecase.film.GetFilmsByCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(private val getFilmsByCharacterUseCase: GetFilmsByCharacterUseCase) :
    ViewModel()