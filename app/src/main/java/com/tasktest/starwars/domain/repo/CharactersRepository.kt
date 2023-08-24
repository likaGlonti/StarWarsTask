package com.tasktest.starwars.domain.repo

import androidx.paging.PagingData
import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.SpecieResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<CharacterResponse>>
    suspend fun getSpecies(byId: String): ResponseResult<SpecieResponse>
}
