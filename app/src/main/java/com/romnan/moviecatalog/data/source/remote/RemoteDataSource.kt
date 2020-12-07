package com.romnan.moviecatalog.data.source.remote

import android.util.Log
import com.romnan.moviecatalog.data.source.remote.api.ApiConfig
import com.romnan.moviecatalog.data.source.remote.api.ApiService
import com.romnan.moviecatalog.data.model.DiscoverResponse
import com.romnan.moviecatalog.data.model.MovieDetail
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    fun getPopularMovies(callback: LoadPopularMoviesCallback) {
        val client = ApiConfig.getApiService().getPopularMovies(ApiService.API_KEY)

        val moviesList = ArrayList<PopularShow>()

        client.enqueue(object : Callback<DiscoverResponse> {
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { moviesList.addAll(it) }
                    callback.onPopularMoviesReceived(moviesList)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DiscoverResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularTvSeries(callback: LoadPopularTvSeriesCallback) {
        val client = ApiConfig.getApiService().getPopularTvSeries(ApiService.API_KEY)

        val tvSeriesList = ArrayList<PopularShow>()

        client.enqueue(object : Callback<DiscoverResponse> {
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { tvSeriesList.addAll(it) }
                    Log.d(TAG, "received data size: ${response.body()?.results?.size}")
                    callback.onPopularTvSeriesReceived(tvSeriesList)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DiscoverResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getMovieDetail(movieId: String, callback: LoadMovieDetailCallback) {
        val client =
            ApiConfig.getApiService().getMovieDetail(movieId, ApiService.API_KEY, ApiService.VIDEOS)

        lateinit var movieDetail: MovieDetail

        client.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    movieDetail = response.body()!!
                    callback.onMovieDetailReceived(movieDetail)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvSeriesDetail(tvShowId: String, callback: LoadTvSeriesDetailCallback) {
        val client = ApiConfig.getApiService()
            .getTvSeriesDetail(tvShowId, ApiService.API_KEY, ApiService.VIDEOS)

        lateinit var tvSeriesDetail: TvSeriesDetail

        client.enqueue(object : Callback<TvSeriesDetail> {
            override fun onResponse(
                call: Call<TvSeriesDetail>,
                response: Response<TvSeriesDetail>
            ) {
                if (response.isSuccessful) {
                    tvSeriesDetail = response.body()!!
                    callback.onTvSeriesDetailReceived(tvSeriesDetail)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvSeriesDetail>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface LoadPopularMoviesCallback {
        fun onPopularMoviesReceived(moviesResponse: List<PopularShow>)
    }

    interface LoadPopularTvSeriesCallback {
        fun onPopularTvSeriesReceived(tvSeriesResponse: List<PopularShow>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetail)
    }

    interface LoadTvSeriesDetailCallback {
        fun onTvSeriesDetailReceived(tvSeriesDetailResponse: TvSeriesDetail)
    }
}