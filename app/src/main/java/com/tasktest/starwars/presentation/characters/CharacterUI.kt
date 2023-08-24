package com.tasktest.starwars.presentation.characters

import androidx.compose.ui.graphics.Color

data class CharacterUI(
    val icon: CharacterIconType,
    val name: String,
    val height: String,
    val mass: String,
    val eyeColor: Color = Color.White
)