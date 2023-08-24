package com.tasktest.starwars.data.pagination

import androidx.paging.PagingData
import com.tasktest.starwars.data.model.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRemoteDataSource {
    fun getStarWarsCharacters(): Flow<PagingData<CharacterResponse>>

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}
