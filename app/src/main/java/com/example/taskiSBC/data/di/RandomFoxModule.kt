package com.example.taskiSBC.data.di

import android.content.Context
import com.example.taskiSBC.R
import com.example.taskiSBC.data.remote.api.RandomFoxApi
import com.example.taskiSBC.data.repository.RandomFoxRepositoryImp
import com.example.taskiSBC.domain.repository.RandomFoxRepository
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RandomFoxModule {

    @Singleton
    @Provides
    fun getRandomFoxApiApi(
        @ApplicationContext context: Context
    ): RandomFoxApi {
        val gson = GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(context.getString(R.string.base_url))//url to hit all apis
            .build()
        return retrofit.create(RandomFoxApi::class.java)
    }

    @Singleton
    @Provides
    fun getRandomFoxRepository(
        api: RandomFoxApi
    ): RandomFoxRepository = RandomFoxRepositoryImp(api = api)


}