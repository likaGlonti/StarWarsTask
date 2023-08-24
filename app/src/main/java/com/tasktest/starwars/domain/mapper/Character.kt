package com.tasktest.starwars.domain.mapper

import com.tasktest.starwars.CharacterIconType
import com.tasktest.starwars.data.model.EyeColor

data class Character(
    val icon: CharacterIconType,
    val name: String,
    val height: String,
    val mass: String,
    val eyeColor: EyeColor = EyeColor.Golden
)