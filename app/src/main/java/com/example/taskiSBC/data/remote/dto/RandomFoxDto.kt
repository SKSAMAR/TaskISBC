package com.example.taskiSBC.data.remote.dto

import com.example.taskiSBC.domain.model.RandomFoxModel
import com.google.gson.annotations.SerializedName


data class RandomFoxDto(
    @SerializedName("image")
    val image: String,
    @SerializedName("link")
    val link: String
)

fun RandomFoxDto.toRandomFoxModel(): RandomFoxModel = RandomFoxModel(image = image, link = link)