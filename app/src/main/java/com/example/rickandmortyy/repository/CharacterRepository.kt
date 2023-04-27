package com.example.rickandmortyy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.paging.CharacterPagingSource
import com.example.rickandmortyy.paging.CharacterSearchPagingSource
import com.example.rickandmortyy.remote.RickAndMortyApiService
import com.example.rickandmortyy.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyApiService: RickAndMortyApiService
) {

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
            ),
            pagingSourceFactory = { CharacterPagingSource(rickAndMortyApiService) }
        ).flow
    }

    fun searchCharacters(query: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
            ),
            pagingSourceFactory = { CharacterSearchPagingSource(rickAndMortyApiService = rickAndMortyApiService, query = query) }
        ).flow
    }


     suspend fun getCharacterDetails(id: Int): Character {
        return rickAndMortyApiService.getCharacterDetails(id)
     }

}
