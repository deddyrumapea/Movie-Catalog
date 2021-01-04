package com.romnan.moviecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    var posterPath: String,
    var title: String,
    var releaseDate: String,
    var overview: String,
    var backdropPath: String,
    var revenue: Int,
    var budget: Int,
    var runtime: Int,
    var voteAverage: Double,
    var tagline: String,
    var status: String,
    var isFavorite: Boolean = false
)