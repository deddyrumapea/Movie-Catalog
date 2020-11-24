package com.romnan.moviecatalog.api

import com.romnan.moviecatalog.model.MovieDetail
import com.romnan.moviecatalog.model.PopShowResponse
import com.romnan.moviecatalog.model.TvShowDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object{
        val apiKey = "51a6c6939964995030fb073e1bc86edf"
    }

    @GET("discover/movie")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopShowResponse>

    @GET("discover/tv")
    fun getPopularTvShows(@Query("api_key") apiKey: String): Call<PopShowResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Query("api_key") apiKey: String,
        @Path("id") id: String
    ): Call<MovieDetail>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Query("api_key") apiKey: String,
        @Path("id") id: String
    ): Call<TvShowDetail>

}