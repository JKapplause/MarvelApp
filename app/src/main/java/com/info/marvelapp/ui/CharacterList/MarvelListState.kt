package com.info.marvelapp.ui.CharacterList

import com.info.marvelapp.domain.model.Character

data class MarvelListState(

    val isLoading :Boolean = false,
    val characterList : List<Character> = emptyList(),
    val error : String = " "
)
