package com.example.taskiSBC.data.remote.api

import com.example.taskiSBC.data.remote.dto.RandomFoxDto
import com.example.taskiSBC.util.Constants
import retrofit2.http.GET

interface RandomFoxApi {

    @GET(Constants.END_POINT)
    suspend fun getRandomFoxData(): RandomFoxDto
}