package com.example.rickandmortyy.remote

import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.model.CharacterListResponse
import com.example.rickandmortyy.model.CharacterSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService  {
    @GET("character")
    suspend fun getCharacters(@Query("page") page : Int ): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Character>

    @GET("character")
    suspend fun searchCharacters(
        @Query("name") name: String,
    ): CharacterSearchResult
}

