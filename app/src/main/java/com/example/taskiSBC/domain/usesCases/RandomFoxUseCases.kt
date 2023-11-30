package com.example.taskiSBC.domain.usesCases

import com.example.taskiSBC.data.remote.dto.toRandomFoxModel
import com.example.taskiSBC.domain.model.RandomFoxModel
import com.example.taskiSBC.domain.repository.RandomFoxRepository
import com.example.taskiSBC.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RandomFoxUseCases
@Inject constructor(private val repository: RandomFoxRepository) {

    fun getApiRandomFox(): Flow<Resource<RandomFoxModel>> = flow {
        try {
            emit(Resource.Loading())
                val dataTimeDto = repository.getRandomFox()
                emit(Resource.Success(dataTimeDto.toRandomFoxModel()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage
                        ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }
    }
}