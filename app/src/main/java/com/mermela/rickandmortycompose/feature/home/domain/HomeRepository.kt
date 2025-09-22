package com.mermela.rickandmortycompose.feature.home.domain

import com.mermela.rickandmortycompose.core.network.ApiResult
import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse

interface HomeRepository {
    suspend fun getCharactersByPage(page: Int) : ApiResult<GetCharactersByPageResponse,String>
}