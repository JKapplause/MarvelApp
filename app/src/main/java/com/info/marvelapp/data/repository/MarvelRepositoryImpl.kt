package com.info.marvelapp.data.repository

import com.info.marvelapp.data.data_source.MarvelApi
import com.info.marvelapp.data.data_source.dto.CharactersDTO
import com.info.marvelapp.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api :MarvelApi
) :MarvelRepository{
    override suspend fun getAllCharacter(offset: Int): CharactersDTO {
        return api.getAllCharacters(offset = offset.toString())
    }
}