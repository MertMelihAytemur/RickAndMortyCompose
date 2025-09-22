package com.mermela.rickandmortycompose.core.network

sealed interface ApiState<out T> {
    object Initial : ApiState<Nothing>
    data class Success<out T>(val response: T) : ApiState<T>
    data class Error(val error: String) : ApiState<Nothing>
}
