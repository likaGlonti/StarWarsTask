package com.tasktest.starwars.data

import com.tasktest.starwars.data.error.ResponseResult
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.PagedDataResponse
import com.tasktest.starwars.data.model.SpecieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("people")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): PagedDataResponse<CharacterResponse>

    @GET("species/{id}")
    suspend fun getSpecie(@Path("id") id: String): SpecieResponse
}
