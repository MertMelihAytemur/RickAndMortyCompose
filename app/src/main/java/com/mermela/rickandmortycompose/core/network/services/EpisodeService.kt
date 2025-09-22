package com.mermela.rickandmortycompose.core.network.services

import com.mermela.rickandmortycompose.model.EpisodeDto
import com.mermela.rickandmortycompose.model.GetEpisodesByPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeService {

    @GET(END_POINT_GET_EPISODES)
    suspend fun getEpisodesByPage(
        @Query("page") page: Int
    ): Response<GetEpisodesByPageResponse>

    @GET("$END_POINT_GET_EPISODES/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): Response<EpisodeDto>

    @GET("$END_POINT_GET_EPISODES/{ids}")
    suspend fun getEpisodesByIds(
        @Path("ids") idsCsv: String
    ): Response<List<EpisodeDto>>

    @GET(END_POINT_GET_EPISODES)
    suspend fun filterEpisodes(
        @Query("name") name: String? = null,
        @Query("episode") episodeCode: String? = null, // ör: S01E10
        @Query("page") page: Int? = null
    ): Response<GetEpisodesByPageResponse>

    private companion object {
        const val END_POINT_GET_EPISODES = "episode"
    }
}