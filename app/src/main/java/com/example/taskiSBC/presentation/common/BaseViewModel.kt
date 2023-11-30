package com.example.taskiSBC.presentation.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.taskiSBC.domain.model.ScreenState

open class BaseViewModel<T> : ViewModel() {

    protected val _state = mutableStateOf(ScreenState<T>())
    val state: State<ScreenState<T>> = _state


}