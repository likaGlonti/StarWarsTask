package com.tasktest.starwars.domain.usecase.film

import com.tasktest.starwars.data.repo.CharactersRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmsByCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(ids: List<Int>) = flow {
        ids.forEach {
            emit(repository.getFilmById(it.toString()))
        }
    }
}