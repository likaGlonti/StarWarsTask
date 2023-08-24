package com.tasktest.starwars.feature.characters

import androidx.compose.ui.graphics.Color
import com.tasktest.starwars.data.model.EyeColor
import com.tasktest.starwars.domain.mapper.Character
import com.tasktest.starwars.ui.theme.Amber
import com.tasktest.starwars.ui.theme.Brown
import com.tasktest.starwars.ui.theme.Golden
import com.tasktest.starwars.ui.theme.Hazel
import com.tasktest.starwars.ui.theme.Orange
import com.tasktest.starwars.ui.theme.lightBlue

fun EyeColor.toColorUI(): Color {
    return when (this) {
        EyeColor.Brown -> Brown
        EyeColor.Blue -> lightBlue
        EyeColor.Green -> Color.Green
        EyeColor.Hazel -> Hazel
        EyeColor.Gray -> Color.Gray
        EyeColor.Amber -> Amber
        EyeColor.Yellow -> Color.Yellow
        EyeColor.Golden -> Golden
        EyeColor.Red -> Color.Red
        EyeColor.Black -> Color.Black
        EyeColor.Orange -> Orange
    }
}

fun Character.mapToUI() = CharacterUI(
    icon = this.icon,
    name = this.name,
    height = height,
    mass = mass,
    eyeColor = eyeColor.toColorUI()
)