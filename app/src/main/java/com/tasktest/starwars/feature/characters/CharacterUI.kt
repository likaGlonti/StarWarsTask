package com.tasktest.starwars.feature.characters

import androidx.compose.ui.graphics.Color
import com.tasktest.starwars.CharacterIconType

data class CharacterUI(
    val icon: CharacterIconType,
    val name: String,
    val height: String,
    val mass: String,
    val eyeColor: Color = Color.White
)