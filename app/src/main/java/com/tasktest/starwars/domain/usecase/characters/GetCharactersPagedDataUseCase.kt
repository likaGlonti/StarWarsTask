package com.tasktest.starwars.domain.usecase.characters

import androidx.paging.PagingData
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.repo.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersPagedDataUseCase @Inject constructor(private val repository: CharactersRepository) {
    operator fun invoke(): Flow<PagingData<CharacterResponse>> {
        return repository.getCharacters()
    }
}