package com.berkay.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<State, Event, Effect>(
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState by lazy {
        MutableStateFlow(value = initialState(savedStateHandle = savedStateHandle))
    }
    val uiState: StateFlow<State> get() = _uiState.asStateFlow()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    protected fun updateState(block: State.() -> State) {
        _uiState.value = _uiState.value.block()
    }

    protected fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    abstract fun handleEvent(event: Event)
    abstract fun initialState(savedStateHandle: SavedStateHandle): State
}
