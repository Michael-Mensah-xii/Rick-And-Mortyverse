package com.example.rickandmortyy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("episode")
    var episode: List<String>,
    @SerialName("image")
    val imageUrl: String,
    @SerialName("origin")
    val origin: CharacterOrigin,
    @SerialName("location")
    val location: CharacterLocation,
)
