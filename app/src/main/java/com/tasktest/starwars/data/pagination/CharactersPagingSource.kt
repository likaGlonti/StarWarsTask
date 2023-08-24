package com.tasktest.starwars.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tasktest.starwars.data.remote.StarWarsApiService
import com.tasktest.starwars.data.error.NoMorePagesLeftToLoadException
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSource.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE_INDEX = 1

class CharactersPagingSource(
    private val service: StarWarsApiService
) : PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val pageIndex = params.key ?: START_PAGE_INDEX
            val response = service.getCharacters(pageIndex)

            response.nextPageURl
                ?: throw NoMorePagesLeftToLoadException("We have no more data to display for today")


            val characters = response.results
            val nextKey = if (characters.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = characters,
                prevKey = if (pageIndex == START_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: NoMorePagesLeftToLoadException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition
    }
}
