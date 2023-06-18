package com.gmribas.desafioverity.presentation.common

import androidx.lifecycle.ViewModel
import com.gmribas.desafioverity.domain.common.ResultUseCase
import com.gmribas.desafioverity.domain.exception.UseCaseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T : Any> : ViewModel() {

    protected val errorHandler = CoroutineExceptionHandler { _, exception ->
        ResultUseCase.Error(UseCaseException.createFromThrowable(exception))
        submitState(UiState.Error())
    }

    private val _viewState: MutableStateFlow<UiState<T>> by lazy {
        MutableStateFlow(setInitialState())
    }

    val viewState: StateFlow<UiState<T>> = _viewState

    private val initialState: UiState<T> by lazy { setInitialState() }

    private fun setInitialState() = UiState.Default

    protected fun submitState(state: UiState<T>) {
        _viewState.value = state
    }
}
