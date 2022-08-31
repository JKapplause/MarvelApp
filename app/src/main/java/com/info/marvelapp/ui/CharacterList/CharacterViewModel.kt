package com.info.marvelapp.ui.CharacterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.marvelapp.domain.repository.use_cases.CharactersUseCase
import com.info.marvelapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase : CharactersUseCase
) : ViewModel(){

    private val marvelValue = MutableStateFlow(MarvelListState())
    var _marvelValue : StateFlow<MarvelListState> = marvelValue

    fun getAllCharactersData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        characterUseCase(offset = offset).collect{
            when(it) {
                is Response.Success -> {
                    marvelValue.value = MarvelListState(characterList = it.data?: emptyList())
                }
                is Response.Loading ->{
                    marvelValue.value = MarvelListState(isLoading = true)
                }
                is Response.Error -> {
                    marvelValue.value = MarvelListState(error = it.message?:"An unexpected Error!")
                }
            }
        }
    }
}