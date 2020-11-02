package com.romnan.moviecatalog.model

data class Movie(
    var id: String,
    var poster: Int,
    var title: String,
    var releaseDate: String,
    var genre: String,
    var duration: String,
    var score: Int,
    var tagline: String,
    var overview: String,
    var director: String,
    var casts: String,
    var status: String,
    var language: String,
    var budget: String,
    var revenue: String,
    var keyword: String
)