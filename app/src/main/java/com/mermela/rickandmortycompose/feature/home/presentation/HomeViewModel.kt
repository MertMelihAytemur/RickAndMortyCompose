package com.mermela.rickandmortycompose.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.mermela.rickandmortycompose.core.CoreViewModel
import com.mermela.rickandmortycompose.feature.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : CoreViewModel() {

    init {
        viewModelScope.launch {
            homeRepository.getCharactersByPage(1)
        }
    }
}