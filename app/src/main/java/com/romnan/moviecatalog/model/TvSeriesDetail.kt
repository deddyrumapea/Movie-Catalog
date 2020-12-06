package com.romnan.moviecatalog.model

import com.google.gson.annotations.SerializedName

data class TvSeriesDetail(
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

	@field:SerializedName("videos")
	val videos: Videos,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("created_by")
	val createdBy: List<CreatedByItem>,

	@field:SerializedName("networks")
	val networks: List<NetworksItem>,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem>,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("last_episode_to_air")
	val lastEpisodeToAir: LastEpisodeToAir,

	@field:SerializedName("production_countries")
	val productionCountries: List<ProductionCountriesItem>
)