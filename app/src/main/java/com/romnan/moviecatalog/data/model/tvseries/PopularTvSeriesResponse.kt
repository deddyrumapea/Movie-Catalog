package com.romnan.moviecatalog.data.model.tvseries

import com.google.gson.annotations.SerializedName

data class PopularTvSeriesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<PopularTvSeries>,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class PopularTvSeries(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null
)
