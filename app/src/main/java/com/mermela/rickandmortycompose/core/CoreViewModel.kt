package com.mermela.rickandmortycompose.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mermela.rickandmortycompose.core.network.ApiResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class CoreViewModel : ViewModel() {

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun <T> launchRequest(
        delay: Long? = null,
        showLoading: Boolean = true,
        request: suspend () -> ApiResult<T, String>,
        onSuccess: suspend (T) -> Unit,
        onError: suspend (String) -> Unit,
    ): Job {
        if (showLoading) {
            _isLoading.value = true
        }
        return viewModelScope.launch {
            delay?.let {
                delay(it)
            }
            when (val result = request()) {
                is ApiResult.Success -> {
                    if (showLoading) {
                        _isLoading.value = false
                    }
                    result.response?.let { response ->
                        onSuccess(response)
                    }
                }

                is ApiResult.Error -> {
                    if (showLoading) {
                        _isLoading.value = false
                    }
                    onError(result.error.toString())
                }
            }
        }
    }
}