package com.example.rickandmortyy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListResponse(
   /* @SerialName("info")
    val info: Info,*/

    @SerialName("results")
    val results: List<Character> = emptyList(),
)
