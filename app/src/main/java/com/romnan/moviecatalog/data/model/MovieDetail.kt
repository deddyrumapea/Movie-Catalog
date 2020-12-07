package com.romnan.moviecatalog.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("revenue")
    val revenue: Int,

    @field:SerializedName("budget")
    val budget: Int,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem>,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("videos")
    val videos: Videos,

    @field:SerializedName("status")
    val status: String
)