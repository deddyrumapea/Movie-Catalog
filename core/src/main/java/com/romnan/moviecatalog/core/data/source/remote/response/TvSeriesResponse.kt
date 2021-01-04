package com.romnan.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("poster_path")
    val posterPath: String = "null",

    @field:SerializedName("backdrop_path")
    val backdropPath: String = "null",

    @field:SerializedName("name")
    val name: String = "null",

    @field:SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @field:SerializedName("tagline")
    val tagline: String = "null",

    @field:SerializedName("first_air_date")
    val firstAirDate: String = "null",

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0,

    @field:SerializedName("overview")
    val overview: String = "null",

    @field:SerializedName("status")
    val status: String = "null",

    @field:SerializedName("type")
    val type: String = "null"
)