package com.vallem.lightningnodes.presentation.util

sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Failure(val throwable: Throwable) : UiState<Nothing>
    data object Idle : UiState<Nothing>

    enum class Loading : UiState<Nothing> {
        Initial, Refresh
    }
}