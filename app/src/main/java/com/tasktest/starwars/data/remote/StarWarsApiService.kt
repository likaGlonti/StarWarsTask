package com.tasktest.starwars.data.remote

import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.FilmResponse
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

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") id: String): FilmResponse
}
