package com.example.taskiSBC.presentation.mainscreen

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.viewModelScope
import com.example.taskiSBC.domain.model.RandomFoxModel
import com.example.taskiSBC.domain.model.ScreenState
import com.example.taskiSBC.domain.usesCases.RandomFoxUseCases
import com.example.taskiSBC.presentation.common.BaseViewModel
import com.example.taskiSBC.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val useCases: RandomFoxUseCases) : BaseViewModel<RandomFoxModel>() {

    init {
        getDateTimeFromApi()
    }

    private fun getDateTimeFromApi() {
        useCases.getApiRandomFox().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = ScreenState(receivedResponse = it.data)
                }

                is Resource.Loading -> {
                    _state.value = ScreenState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = ScreenState(error = it.message ?: "Some Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeRandomFox() {
        getDateTimeFromApi()
    }

    fun openWithChromeTab(context: Context){
        val customIntent =  CustomTabsIntent.Builder().build();
        customIntent.launchUrl(context, Uri.parse(_state.value.receivedResponse?.link?:""))
    }

}