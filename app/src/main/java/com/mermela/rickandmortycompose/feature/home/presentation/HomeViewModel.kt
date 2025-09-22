package com.mermela.rickandmortycompose.feature.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.mermela.rickandmortycompose.core.CoreViewModel
import com.mermela.rickandmortycompose.core.network.ApiState
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import com.mermela.rickandmortycompose.feature.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : CoreViewModel() {

    private val _charactersState = mutableStateOf<ApiState<GetCharactersByPageResponse>>(ApiState.Initial)
    val charactersState: State<ApiState<GetCharactersByPageResponse>> get() = _charactersState

    init {
        fetchCharacters()
    }

    fun fetchCharacters(page: Int = 1) {
        viewModelScope.launch {
            launchRequest(
                showLoading = true,
                request = { homeRepository.getCharactersByPage(page) },
                onSuccess = { response ->
                    _charactersState.value = ApiState.Success(response)
                },
                onError = { error ->
                    _charactersState.value = ApiState.Error(error)
                }
            )
        }
    }
}