package com.tasktest.starwars.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.tasktest.starwars.domain.mapper.Character
import com.tasktest.starwars.domain.mapper.mapTo
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
                    getSpeciesUseCase(takeOutSpecieID(character.species))
                } else null
                character.mapTo(specie)
            }
        }
    }

    private fun takeOutSpecieID(speciesURls: List<String>): String {
        return speciesURls.first().toCharArray().let {
            it[it.size - 2].toString()
        }
    }
}
