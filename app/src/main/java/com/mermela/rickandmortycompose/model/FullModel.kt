package com.mermela.rickandmortycompose.model

data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterOriginOrLocationRef(
    val name: String,
    val url: String
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,      // Alive | Dead | unknown
    val species: String,
    val type: String,
    val gender: String,      // Female | Male | Genderless | unknown
    val origin: CharacterOriginOrLocationRef,
    val location: CharacterOriginOrLocationRef,
    val image: String,
    val episode: List<String>, // episode url'leri
    val url: String,
    val created: String
)

data class GetCharactersByPageResponse(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>, // character url'leri
    val url: String,
    val created: String
)

data class GetLocationsByPageResponse(
    val info: PageInfoDto,
    val results: List<LocationDto>
)

data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,         // S01E01
    val characters: List<String>,// character url'leri
    val url: String,
    val created: String
)

data class GetEpisodesByPageResponse(
    val info: PageInfoDto,
    val results: List<EpisodeDto>
)

fun List<Int>.asCsv(): String = joinToString(",")