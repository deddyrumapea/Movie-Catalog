package com.romnan.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("title")
    val title: String = "null",

    @field:SerializedName("poster_path")
    val posterPath: String = "null",

    @field:SerializedName("backdrop_path")
    val backdropPath: String = "null",

    @field:SerializedName("revenue")
    val revenue: Int = 0,

    @field:SerializedName("budget")
    val budget: Int = 0,

    @field:SerializedName("overview")
    val overview: String = "null",

    @field:SerializedName("runtime")
    val runtime: Int = 0,

    @field:SerializedName("release_date")
    val releaseDate: String = "null",

    @field:SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @field:SerializedName("tagline")
    val tagline: String = "null",

    @field:SerializedName("status")
    val status: String = "null"
)