package com.romnan.moviecatalog.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_tv_series")
data class TvSeriesDetail(
	@PrimaryKey
	@NonNull
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("type")
	val type: String,
) : Parcelable
