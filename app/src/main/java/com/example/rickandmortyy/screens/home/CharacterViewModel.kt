package com.example.rickandmortyy.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {
    fun getCharacters(): Flow<PagingData<Character>> {
        return repository.getCharacters()
            .cachedIn(viewModelScope)
    }

    /*suspend fun getCharacterDetails(id: Int): Character {
        return repository.getCharacterDetails(id)
            .body() ?: throw Exception("Failed to get character details")
    }*/
}

