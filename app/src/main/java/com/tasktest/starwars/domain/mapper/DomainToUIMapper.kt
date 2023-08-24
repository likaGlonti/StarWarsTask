package com.tasktest.starwars.domain.mapper

import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.domain.utils.takeOutId
import com.tasktest.starwars.presentation.characters.CharacterIconType

fun CharacterResponse.mapTo(specie: SpecieResponse?) = Character(
    icon = specie?.let { CharacterIconType.getType(it) } ?: CharacterIconType.Human,
    name = name,
    height = height,
    mass = mass,
    filmIDs = this.films.map { it.takeOutId() },
    eyeColor = eyeColor
)


