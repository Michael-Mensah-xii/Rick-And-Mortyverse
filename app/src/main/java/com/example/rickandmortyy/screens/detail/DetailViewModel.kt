package com.example.rickandmortyy.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private var _characterDetails = MutableLiveData<Character>()
    val characterDetails: LiveData<Character>
        get() = _characterDetails

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    suspend fun getCharacterDetails(id: Int) {
        _isLoading.value = true
        try {
            val character = repository.getCharacterDetails(id)
            _characterDetails.value = character
        } catch (e: Exception) {
            _errorMessage.value = "Failed to get character details"
        } finally {
            _isLoading.value = false
        }
    }

    fun retry(id: Int) {
        _errorMessage.value = null
        viewModelScope.launch {
            getCharacterDetails(id)
        }
    }
}




