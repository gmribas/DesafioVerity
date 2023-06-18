package com.gmribas.desafioverity.presentation.common

import com.gmribas.desafioverity.domain.common.ResultUseCase

abstract class CommonResultConverter<T : Any, R : Any> {

    fun convert(result: ResultUseCase<T>): UiState<R> {
        return when (result) {
            is ResultUseCase.Error -> {
                UiState.Error(result.exception.localizedMessage.orEmpty())
            }
            is ResultUseCase.Success -> {
                UiState.Success(convertSuccess(result.data))
            }
        }
    }

    abstract fun convertSuccess(data: T): R
}
