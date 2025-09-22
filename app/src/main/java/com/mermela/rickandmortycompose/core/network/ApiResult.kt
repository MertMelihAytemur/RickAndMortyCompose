package com.mermela.rickandmortycompose.core.network

sealed class ApiResult<out T, out E> {
    class Success<T>(val response: T?) : ApiResult<T, Nothing>()
    class Error<E>(val error: String?) : ApiResult<Nothing, E>()
}