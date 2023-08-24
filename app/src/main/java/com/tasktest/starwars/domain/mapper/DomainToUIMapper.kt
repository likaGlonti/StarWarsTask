package com.tasktest.starwars.domain.mapper

import com.tasktest.starwars.presentation.characters.CharacterIconType
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.SpecieResponse

fun CharacterResponse.mapTo(specie: SpecieResponse?) = Character(
    icon = specie?.let { CharacterIconType.getType(it) } ?: CharacterIconType.Human,
    name = name,
    height = height,
    mass = mass,
    eyeColor = eyeColor
)


