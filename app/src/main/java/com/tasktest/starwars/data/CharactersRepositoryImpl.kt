package com.tasktest.starwars.data

import androidx.paging.PagingData
import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSource
import com.tasktest.starwars.domain.repo.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val service: StarWarsApiService
) : CharactersRepository {
    override fun getCharacters(): Flow<PagingData<CharacterResponse>> {
        return remoteDataSource.getStarWarsCharacters()
    }

    override suspend fun getSpecies(byId: String): ResponseResult<SpecieResponse> {
        return try {
            ResponseResult.Success(service.getSpecie(byId))
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }
}
