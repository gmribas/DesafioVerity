package com.gmribas.desafioverity.domain.common

import com.gmribas.desafioverity.domain.exception.UseCaseException

sealed class ResultUseCase<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultUseCase<T>()
    class Error(val exception: UseCaseException) : ResultUseCase<Nothing>()
}
