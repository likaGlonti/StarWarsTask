package com.tasktest.starwars.domain.usecase.film

import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.FilmResponse
import com.tasktest.starwars.data.repo.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmsByCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(ids: List<Int>): Flow<List<FilmResponse>> = flow {
        val films = arrayListOf<FilmResponse>().apply {
            ids.onEach {
                val response = repository.getFilmById(it.toString())
                val film = if (response is ResponseResult.Success)
                    response.data
                else FilmResponse("", "")
                add(film)
            }
        }
        emit(films)
    }
}