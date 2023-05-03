package com.example.rickandmortyy.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterOrigin(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
