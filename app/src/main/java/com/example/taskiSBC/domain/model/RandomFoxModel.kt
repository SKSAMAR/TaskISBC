package com.example.taskiSBC.domain.model

import com.example.taskiSBC.data.remote.dto.RandomFoxDto

data class RandomFoxModel(
    val image: String,
    val link: String
)

fun RandomFoxModel.toRandomFoxDto(): RandomFoxDto = RandomFoxDto(image = image, link = link)