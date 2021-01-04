package com.romnan.moviecatalog.core.data.source.remote.api

import com.romnan.moviecatalog.core.data.source.remote.response.MovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.PopularMovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.PopularTvSeriesResponse
import com.romnan.moviecatalog.core.data.source.remote.response.TvSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val API_KEY = "51a6c6939964995030fb073e1bc86edf"
    }

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): PopularMovieResponse

    @GET("discover/tv")
    suspend fun getPopularTvSeries(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): PopularTvSeriesResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse

    @GET("tv/{id}")
    suspend fun getTvSeriesDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): TvSeriesResponse

}