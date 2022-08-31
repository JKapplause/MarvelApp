package com.info.marvelapp.domain.repository

import com.info.marvelapp.data.data_source.dto.CharactersDTO

interface MarvelRepository {

    suspend fun getAllCharacter(offset: Int): CharactersDTO
}