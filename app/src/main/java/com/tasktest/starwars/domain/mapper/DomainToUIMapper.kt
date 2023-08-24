package com.tasktest.starwars.domain.mapper

import com.tasktest.starwars.CharacterIconType
import com.tasktest.starwars.CharacterUI
import com.tasktest.starwars.data.model.CharacterResponse
import com.tasktest.starwars.data.model.SpecieResponse
import com.tasktest.starwars.toColorUI

fun CharacterResponse.mapDomainToUI(specie: SpecieResponse?) = CharacterUI(
    icon = specie?.let { CharacterIconType.getType(it) } ?: CharacterIconType.Human,
    name = this.name,
    height = this.height,
    mass = this.mass,
    eyeColor = this.eyeColor.toColorUI()
)


