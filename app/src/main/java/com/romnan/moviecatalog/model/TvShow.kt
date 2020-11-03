package com.romnan.moviecatalog.model

data class TvShow(
    var id: String,
    var poster: Int,
    var title: String,
    var trailerUrl: String,
    var genre: String,
    var duration: String,
    var score: Int,
    var tagline: String,
    var overview: String,
    var creator: String,
    var casts: String,
    var status: String,
    var type: String,
    var language: String,
    var keyword: String,
    var lastSeason: String
)