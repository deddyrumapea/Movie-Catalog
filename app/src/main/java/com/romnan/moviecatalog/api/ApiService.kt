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
        const val API_KEY = "51a6c6939964995030fb073e1bc86edf"
        const val VIDEOS = "videos"
    }

    @GET("discover/movie")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopShowResponse>

    @GET("discover/tv")
    fun getPopularTvShows(@Query("api_key") apiKey: String): Call<PopShowResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String
    ): Call<MovieDetail>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String
    ): Call<TvShowDetail>

}