package com.example.rickandmortyy.remote

import com.example.rickandmortyy.data.model.Character
import com.example.rickandmortyy.data.model.CharacterListResponse
import com.example.rickandmortyy.data.model.CharacterSearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page:Int): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Character

    @GET("character/?")
    suspend fun searchCharacters(
        @Query("name") name: String,
    ): CharacterSearchResult
}

