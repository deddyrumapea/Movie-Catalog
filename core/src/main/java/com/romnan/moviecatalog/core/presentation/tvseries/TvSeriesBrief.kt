package com.romnan.moviecatalog.core.presentation.tvseries

data class TvSeriesBrief(
    val id: Int,
    val posterPath: String,
    val name: String,
    val firstAirDate: String,
    val overview: String
)