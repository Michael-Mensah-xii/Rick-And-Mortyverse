package com.example.rickandmortyy.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterSearchResult(
    @SerialName("results")
    val results: List<Character> = listOf(),
)



