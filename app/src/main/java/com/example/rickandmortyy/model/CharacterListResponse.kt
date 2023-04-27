package com.example.rickandmortyy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListResponse(
    @SerialName("results")
    val results: List<Character> = emptyList(),
)
