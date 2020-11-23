package com.romnan.moviecatalog.model

import com.google.gson.annotations.SerializedName

data class PopShow(
	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null
)