package com.mermela.rickandmortycompose.feature.home.data

import com.mermela.rickandmortycompose.core.network.ApiResult
import com.mermela.rickandmortycompose.core.network.safeApiCall
import com.mermela.rickandmortycompose.core.network.services.CharacterService
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import com.mermela.rickandmortycompose.feature.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
) : HomeRepository{
    override suspend fun getCharactersByPage(page: Int): ApiResult<GetCharactersByPageResponse, String> {
        return safeApiCall { characterService.getCharactersByPage(page) }
    }
}