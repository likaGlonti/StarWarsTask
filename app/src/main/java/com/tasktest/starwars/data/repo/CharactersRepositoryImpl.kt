package com.tasktest.starwars.data.repo

import androidx.paging.PagingData
import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.FilmResponse
import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSource
import com.tasktest.starwars.data.remote.StarWarsApiService
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

    override suspend fun getFilmById(id: String): ResponseResult<FilmResponse> {
        return try {
            ResponseResult.Success(service.getFilmById(id))
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }
}
