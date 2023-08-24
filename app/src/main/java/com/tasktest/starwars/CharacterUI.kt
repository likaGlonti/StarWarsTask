package com.tasktest.starwars

import androidx.compose.ui.graphics.Color
import com.tasktest.starwars.data.model.EyeColor
import com.tasktest.starwars.ui.theme.Amber
import com.tasktest.starwars.ui.theme.Blue
import com.tasktest.starwars.ui.theme.Brown
import com.tasktest.starwars.ui.theme.Golden
import com.tasktest.starwars.ui.theme.Hazel
import com.tasktest.starwars.ui.theme.Orange
import com.tasktest.starwars.ui.theme.lightBlue

data class CharacterUI(
    val icon: CharacterIconType,
    val name: String,
    val height: String,
    val mass: String,
    val eyeColor: Color = Color.White
)

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
