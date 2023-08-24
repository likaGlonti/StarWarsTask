package com.tasktest.starwars.data.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tasktest.starwars.data.StarWarsApiService
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSource.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class CharactersRemoteDataSourceImpl(
    private val remoteService: StarWarsApiService
) : CharactersRemoteDataSource {

    override fun getStarWarsCharacters(): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersPagingSource(service = remoteService) }
        ).flow
    }
}
