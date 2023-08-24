package com.tasktest.starwars.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//Since, Api doesn't give us hex numbers of the colors, I`ve checked mostly sent colors and created enum,
// in UI layer added mapper to convert them in graphics Color.
// used @SerialNames cause response strings doesn't match Enum object names
@Serializable
enum class EyeColor {
    @SerialName("brown")
    Brown,
    @SerialName("blue")
    Blue,
    @SerialName("green")
    Green,
    @SerialName("hazel")
    Hazel,
    @SerialName("gray")
    Gray,
    @SerialName("amber")
    Amber,
    @SerialName("yellow")
    Yellow,
    @SerialName("golden")
    Golden,
    @SerialName("red")
    Red,
    @SerialName("black")
    Black,
    @SerialName("orange")
    Orange,
}


