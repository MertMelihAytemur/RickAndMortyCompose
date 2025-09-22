package com.mermela.rickandmortycompose.feature.home.data.service

import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HomeService {
    @GET(END_POINT_GET_CHARACTERS_BY_PAGE)
    suspend fun getCharactersByPage(
        @Query("page") page: Int
    ) : Response<GetCharactersByPageResponse>

    private companion object {
        const val END_POINT_GET_CHARACTERS_BY_PAGE = "character"
    }
}