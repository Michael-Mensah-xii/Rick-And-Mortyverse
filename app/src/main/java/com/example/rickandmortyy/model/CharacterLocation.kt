package com.example.rickandmortyy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocation(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val locationUrl: String,
)