package com.example.rickandmortyy.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.rickandmortyy.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository,
) : ViewModel() {
    val getCharacters = repository.getCharacters().cachedIn(viewModelScope)
}

