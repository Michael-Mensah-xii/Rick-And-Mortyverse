package com.example.rickandmortyy.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    private var currentQuery: String? = null

    var searchQuery by mutableStateOf("")
        private set

    private val _searchedImages = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    val searchedImages = _searchedImages.asStateFlow()

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun searchStuff(query: String) {
        if (query != currentQuery) {
            // If the search query has changed, reset the LazyPagingItems state
            currentQuery = query
            _searchedImages.value = PagingData.empty()
        }

        viewModelScope.launch {
            repository.searchCharacters(query = query).cachedIn(viewModelScope).collect {
                _searchedImages.value = it
            }
        }
    }
}

