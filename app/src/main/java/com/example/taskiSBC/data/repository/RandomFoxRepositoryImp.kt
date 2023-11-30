package com.example.taskiSBC.data.repository

import com.example.taskiSBC.data.remote.api.RandomFoxApi
import com.example.taskiSBC.data.remote.dto.RandomFoxDto
import com.example.taskiSBC.domain.repository.RandomFoxRepository
import javax.inject.Inject

class RandomFoxRepositoryImp
@Inject constructor(
    private val api: RandomFoxApi,
) : RandomFoxRepository {

    override suspend fun getRandomFox(): RandomFoxDto {
        return api.getRandomFoxData()
    }
}