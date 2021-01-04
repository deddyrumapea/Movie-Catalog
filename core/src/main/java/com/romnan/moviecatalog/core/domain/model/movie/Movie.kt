package com.romnan.moviecatalog.core.domain.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val backdropPath: String,
    val revenue: Int,
    val budget: Int,
    val runtime: Int,
    val voteAverage: Double,
    val tagline: String,
    val status: String,
    val isFavorite: Boolean
) : Parcelable