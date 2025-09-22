package com.mermela.rickandmortycompose.feature.home.contract

import com.mermela.rickandmortycompose.core.network.ApiState
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse

// Defines UI contract for Home feature: state, intents, and one-off events
object HomeContract {
    // UI State shown by HomeScreen
    data class UiState(
        val characters: ApiState<GetCharactersByPageResponse> = ApiState.Initial,
        val isRefreshing: Boolean = false,
        val searchQuery: String = "",
        val currentPage: Int = 1
    )

    // Intents coming from UI to ViewModel
    sealed interface UiIntent {
        data object LoadInitial : UiIntent
        data class LoadPage(val page: Int) : UiIntent
        data object Refresh : UiIntent
        data class Search(val query: String) : UiIntent
        data class CharacterClicked(val id: Int) : UiIntent
    }

    // One-off events from ViewModel to UI (navigation, toasts, etc.)
    sealed interface UiEvent {
        data class ShowError(val message: String) : UiEvent
        data class NavigateToCharacterDetail(val id: Int) : UiEvent
    }
}
