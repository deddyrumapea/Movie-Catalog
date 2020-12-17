package com.romnan.moviecatalog.data.model.movie

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<PopularMovie>,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class PopularMovie(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null
)
