package com.tasktest.starwars.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(val title:String, @SerialName("release_date") val releaseDate: String)
