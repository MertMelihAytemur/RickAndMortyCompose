package com.mermela.rickandmortycompose.core.network.services

import com.mermela.rickandmortycompose.feature.home.data.dto.response.GetCharactersByPageResponse
import com.mermela.rickandmortycompose.model.CharacterDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET(END_POINT_GET_CHARACTERS)
    suspend fun getCharactersByPage(
        @Query("page") page: Int
    ): Response<GetCharactersByPageResponse>

    @GET("$END_POINT_GET_CHARACTERS/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): Response<CharacterDto>

    @GET("$END_POINT_GET_CHARACTERS/{ids}")
    suspend fun getCharactersByIds(
        @Path("ids") idsCsv: String // ids.joinToString(",")
    ): Response<List<CharacterDto>>

    @GET(END_POINT_GET_CHARACTERS)
    suspend fun filterCharacters(
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,      // alive | dead | unknown
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null,      // female | male | genderless | unknown
        @Query("page") page: Int? = null
    ): Response<GetCharactersByPageResponse>

    private companion object {
        const val END_POINT_GET_CHARACTERS = "character"
    }
}