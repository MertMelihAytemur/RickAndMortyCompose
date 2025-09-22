package com.mermela.rickandmortycompose.core.network

import retrofit2.Response

suspend fun <T,E> safeApiCall(request: suspend () -> Response<T>) : ApiResult<T,E> {
    return try {
        val response = request()
        if (response.isSuccessful) {
            response.body()?.let {
                ApiResult.Success(it)
            } ?: ApiResult.Error("An Error Occurred")
        } else {
            val errorBody = response.errorBody()?.string()
            ApiResult.Error(errorBody)
        }
    } catch (e: Exception) {
        ApiResult.Error(e.message)
    }
}