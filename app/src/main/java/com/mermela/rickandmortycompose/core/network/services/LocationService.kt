package com.mermela.rickandmortycompose.core.network.services

import com.mermela.rickandmortycompose.model.GetLocationsByPageResponse
import com.mermela.rickandmortycompose.model.LocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {

    @GET(END_POINT_GET_LOCATIONS)
    suspend fun getLocationsByPage(
        @Query("page") page: Int
    ): Response<GetLocationsByPageResponse>

    @GET("$END_POINT_GET_LOCATIONS/{id}")
    suspend fun getLocation(
        @Path("id") id: Int
    ): Response<LocationDto>

    @GET("$END_POINT_GET_LOCATIONS/{ids}")
    suspend fun getLocationsByIds(
        @Path("ids") idsCsv: String
    ): Response<List<LocationDto>>

    @GET(END_POINT_GET_LOCATIONS)
    suspend fun filterLocations(
        @Query("name") name: String? = null,
        @Query("type") type: String? = null,
        @Query("dimension") dimension: String? = null,
        @Query("page") page: Int? = null
    ): Response<GetLocationsByPageResponse>

    private companion object {
        const val END_POINT_GET_LOCATIONS = "location"
    }
}