package com.romnan.moviecatalog.model

data class Movie(
    val id: String,
    val poster: Int,
    val title: String,
    val trailerUrl : String,
    val releaseDate: String,
    val genre: String,
    val duration: String,
    val score: Int,
    val tagline: String,
    val overview: String,
    val director: String,
    val casts: String,
    val status: String,
    val language: String,
    val budget: String,
    val revenue: String,
    val keyword: String
)