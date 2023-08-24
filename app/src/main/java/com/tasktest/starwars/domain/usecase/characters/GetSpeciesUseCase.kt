package com.tasktest.starwars.domain.usecase.characters

import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.data.repo.CharactersRepository
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(id: String): SpecieResponse {
        return when (val response = repository.getSpecies(id)) {
            is ResponseResult.Success -> response.data
            else -> SpecieResponse("")
        }
    }
}