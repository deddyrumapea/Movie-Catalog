package com.romnan.moviecatalog.core.data.source.remote.api

import com.romnan.moviecatalog.core.BuildConfig
import com.romnan.moviecatalog.core.data.source.remote.response.MovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.DiscoverMovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.DiscoverTvSeriesResponse
import com.romnan.moviecatalog.core.data.source.remote.response.TvSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): DiscoverMovieResponse

    @GET("discover/tv")
    suspend fun getDiscoverTvSeries(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): DiscoverTvSeriesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieResponse

    @GET("tv/{id}")
    suspend fun getTvSeriesDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): TvSeriesResponse
}