package com.mermela.rickandmortycompose.feature.home.data

import com.mermela.rickandmortycompose.core.network.ApiResult
import com.mermela.rickandmortycompose.core.network.safeApiCall
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import com.mermela.rickandmortycompose.feature.home.data.service.HomeService
import com.mermela.rickandmortycompose.feature.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository{
    override suspend fun getCharactersByPage(page: Int): ApiResult<GetCharactersByPageResponse, String> {
        return safeApiCall { homeService.getCharactersByPage(page) }
    }
}