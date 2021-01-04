package com.romnan.moviecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_series")
data class TvSeriesEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    var posterPath: String,
    var name: String,
    var firstAirDate: String,
    var overview: String,
    var backdropPath: String,
    var voteAverage: Double,
    var tagline: String,
    var numberOfSeasons: Int,
    var status: String,
    var type: String,
    var isFavorite: Boolean = false
)