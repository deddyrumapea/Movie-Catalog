package com.romnan.moviecatalog.data.source.remote.api

import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovieResponse
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val API_KEY = "51a6c6939964995030fb073e1bc86edf"
    }

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<PopularMovieResponse>

    @GET("discover/tv")
    fun getPopularTvSeries(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<PopularTvSeriesResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetail>

    @GET("tv/{id}")
    fun getTvSeriesDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<TvSeriesDetail>

}