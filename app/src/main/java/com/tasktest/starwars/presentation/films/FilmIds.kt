package com.tasktest.starwars.presentation.films

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class FilmIds(val ids: List<Int>) : Parcelable
