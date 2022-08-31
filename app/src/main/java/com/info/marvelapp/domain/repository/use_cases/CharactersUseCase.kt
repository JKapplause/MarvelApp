package com.info.marvelapp.domain.repository.use_cases

import com.info.marvelapp.domain.model.Character
import com.info.marvelapp.domain.repository.MarvelRepository
import com.info.marvelapp.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(offset:Int) : Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading<List<Character>>())
            val list= repository.getAllCharacter(offset = offset).data.results.map{
                it.toCharacter()
            }
            emit(Response.Success<List<Character>>(list))
        }catch (e:HttpException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e:IOException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}