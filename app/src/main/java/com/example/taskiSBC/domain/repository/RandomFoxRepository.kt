package com.example.taskiSBC.domain.repository

import com.example.taskiSBC.data.remote.dto.RandomFoxDto

interface RandomFoxRepository {

    suspend fun getRandomFox(): RandomFoxDto
}