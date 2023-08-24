package com.tasktest.starwars.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagedDataResponse<T>(
    private val count: Int,
    @SerialName("next") val nextPageURl: String? = null,
    @SerialName("previous") val previousPageURl: String?,
    val results: List<T>
)