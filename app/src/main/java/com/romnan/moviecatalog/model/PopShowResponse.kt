package com.romnan.moviecatalog.model

import com.google.gson.annotations.SerializedName

data class PopShowResponse(
    @field:SerializedName("results")
    val results: List<PopShow?>? = null
)

data class PopShow(
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