package com.tasktest.starwars.domain.usecase.characters

import androidx.paging.PagingData
import androidx.paging.map
import com.tasktest.starwars.domain.mapper.Character
import com.tasktest.starwars.domain.mapper.mapTo
import com.tasktest.starwars.domain.utils.takeOutIds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMergedDataOfCharacterAndSpecieUseCase @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val charactersUseCase: GetCharactersPagedDataUseCase
) {
    suspend operator fun invoke(): Flow<PagingData<Character>> {
        return charactersUseCase().map {
            it.map { character ->
                val specie = if (character.species.isNotEmpty()) {
                    getSpeciesUseCase(character.species.takeOutIds())
                } else null
                character.mapTo(specie)
            }
        }
    }
}
