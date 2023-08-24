package com.tasktest.starwars.di

import com.tasktest.starwars.data.repo.CharactersRepositoryImpl
import com.tasktest.starwars.data.remote.StarWarsApiService
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSource
import com.tasktest.starwars.data.pagination.CharactersRemoteDataSourceImpl
import com.tasktest.starwars.data.repo.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCharactersRemoteDataSource(starWarsApiService: StarWarsApiService): CharactersRemoteDataSource =
        CharactersRemoteDataSourceImpl(starWarsApiService)

    @Provides
    @Singleton
    fun providesCharactersRepo(
        pagingSource: CharactersRemoteDataSource,
        starWarsApiService: StarWarsApiService
    ): CharactersRepository =
        CharactersRepositoryImpl(pagingSource, starWarsApiService)
}
