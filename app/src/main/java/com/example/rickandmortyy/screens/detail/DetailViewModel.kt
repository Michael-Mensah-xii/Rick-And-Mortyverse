package com.example.rickandmortyy.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyy.model.Character
import com.example.rickandmortyy.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    private val _characterDetails = MutableStateFlow<Character?>(null)
    val characterDetails: StateFlow<Character?>
        get() = _characterDetails

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?>
        get() = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    fun getCharacterDetails(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _characterDetails.value = repository.getCharacterDetails(id)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to get character details"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retry(id: Int) {
        _errorMessage.value = null
        getCharacterDetails(id)
    }
}


