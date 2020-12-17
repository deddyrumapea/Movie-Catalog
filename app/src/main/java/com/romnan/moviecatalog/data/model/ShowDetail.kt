package com.romnan.moviecatalog.data.model

import com.google.gson.annotations.SerializedName
import com.romnan.moviecatalog.data.model.movie.PopularMovie

data class SpokenLanguagesItem(
    @field:SerializedName("name")
    val name: String,
)

data class GenresItem(
    @field:SerializedName("name")
    val name: String
)

data class Videos(
    @field:SerializedName("results")
    val results: List<ResultsItem>
)

data class ResultsItem(
    @field:SerializedName("key")
    val key: String
)