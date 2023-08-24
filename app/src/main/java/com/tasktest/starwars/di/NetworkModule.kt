package com.tasktest.starwars.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tasktest.starwars.data.remote.StarWarsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl() = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun providesHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(
        retrofit: Retrofit
    ): StarWarsApiService = retrofit.create(StarWarsApiService::class.java)
}