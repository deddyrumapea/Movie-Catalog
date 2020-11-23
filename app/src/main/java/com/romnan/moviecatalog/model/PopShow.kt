package com.romnan.moviecatalog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopShow(
	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null
) : Parcelable