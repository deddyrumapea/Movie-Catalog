package com.romnan.moviecatalog.data.source.remote.api

import com.romnan.moviecatalog.data.model.DiscoverResponse
import com.romnan.moviecatalog.data.model.MovieDetail
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val API_KEY = "51a6c6939964995030fb073e1bc86edf"
        const val VIDEOS = "videos"
    }

    @GET("discover/movie?api_key=$API_KEY")
    fun getPopularMovies(): Call<DiscoverResponse>

    @GET("discover/tv?api_key=$API_KEY")
    fun getPopularTvSeries(): Call<DiscoverResponse>

    @GET("movie/{id}?api_key=$API_KEY&append_to_response=$VIDEOS")
    fun getMovieDetail(
        @Path("id") id: String
    ): Call<MovieDetail>

    @GET("tv/{id}?api_key=$API_KEY&append_to_response=$VIDEOS")
    fun getTvSeriesDetail(
        @Path("id") id: String
    ): Call<TvSeriesDetail>

}