package com.gmribas.desafioverity.presentation.common

sealed class UiState<out T> {
    object Default : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: String = "") : UiState<Nothing>()
}
