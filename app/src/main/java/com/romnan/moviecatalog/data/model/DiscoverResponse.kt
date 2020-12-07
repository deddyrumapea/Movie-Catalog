package com.romnan.moviecatalog.data.model

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @field:SerializedName("results")
    val results: List<PopularShow>
)

data class PopularShow(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null
)

data class SpokenLanguagesItem(
    @field:SerializedName("name")
    val name: String,
)

data class ResultsItem(
    @field:SerializedName("key")
    val key: String
)

data class ProductionCountriesItem(
    @field:SerializedName("name")
    val name: String
)

data class LastEpisodeToAir(
    @field:SerializedName("air_date")
    val airDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("episode_number")
    val episodeNumber: Int,

    @field:SerializedName("season_number")
    val seasonNumber: Int
)

data class NetworksItem(
    @field:SerializedName("name")
    val name: String
)

data class GenresItem(
    @field:SerializedName("name")
    val name: String
)

data class CreatedByItem(
    @field:SerializedName("name")
    val name: String
)

data class Videos(
    @field:SerializedName("results")
    val results: List<ResultsItem>
)
