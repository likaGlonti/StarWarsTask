package com.tasktest.starwars.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val name: String,
    val height: String,
    val mass: String,
    val species: List<String> = emptyList(),
    @SerialName("eye_color")
    val eyeColor: EyeColor = EyeColor.Blue
)
