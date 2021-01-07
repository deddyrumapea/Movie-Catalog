package com.romnan.moviecatalog.core.presentation.movie

data class MovieBrief(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String
)