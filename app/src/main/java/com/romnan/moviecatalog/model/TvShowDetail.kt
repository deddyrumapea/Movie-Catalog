package com.romnan.moviecatalog.model

data class TvShowDetail(
    val id: String,
    val poster: Int,
    val title: String,
    val trailerUrl: String,
    val genre: String,
    val duration: String,
    val score: Int,
    val tagline: String,
    val overview: String,
    val creator: String,
    val casts: String,
    val status: String,
    val type: String,
    val language: String,
    val keyword: String,
    val lastSeason: String
)