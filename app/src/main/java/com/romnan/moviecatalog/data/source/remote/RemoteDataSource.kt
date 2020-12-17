package com.romnan.moviecatalog.data.source.remote

import android.util.Log
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.movie.PopularMovieResponse
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeriesResponse
import com.romnan.moviecatalog.data.source.remote.api.ApiConfig
import com.romnan.moviecatalog.utils.EspressoIdlingResource
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
        EspressoIdlingResource.increment() //TODO: delete

        val client = ApiConfig.getApiService().getPopularMovies()

        val moviesList = ArrayList<PopularMovie>()

        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { moviesList.addAll(it) }
                    callback.onPopularMoviesReceived(moviesList)
                    EspressoIdlingResource.decrement() //TODO : delete
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getPopularTvSeries(callback: LoadPopularTvSeriesCallback) {
        EspressoIdlingResource.increment() //TODO: delete

        val client = ApiConfig.getApiService().getPopularTvSeries()

        val tvSeriesList = ArrayList<PopularTvSeries>()

        client.enqueue(object : Callback<PopularTvSeriesResponse> {
            override fun onResponse(
                call: Call<PopularTvSeriesResponse>,
                response: Response<PopularTvSeriesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { tvSeriesList.addAll(it) }
                    callback.onPopularTvSeriesReceived(tvSeriesList)
                    EspressoIdlingResource.decrement() //TODO : delete
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularTvSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment() //TODO: delete

        val client =
            ApiConfig.getApiService().getMovieDetail(movieId)

        lateinit var movieDetail: MovieDetail

        client.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    movieDetail = response.body()!!
                    callback.onMovieDetailReceived(movieDetail)
                    EspressoIdlingResource.decrement() //TODO : delete
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvSeriesDetail(tvShowId: Int, callback: LoadTvSeriesDetailCallback) {
        EspressoIdlingResource.increment() //TODO: delete

        val client = ApiConfig.getApiService()
            .getTvSeriesDetail(tvShowId)

        lateinit var tvSeriesDetail: TvSeriesDetail

        client.enqueue(object : Callback<TvSeriesDetail> {
            override fun onResponse(
                call: Call<TvSeriesDetail>,
                response: Response<TvSeriesDetail>
            ) {
                if (response.isSuccessful) {
                    tvSeriesDetail = response.body()!!
                    callback.onTvSeriesDetailReceived(tvSeriesDetail)
                    EspressoIdlingResource.decrement() //TODO : delete
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
        fun onPopularMoviesReceived(moviesResponse: List<PopularMovie>)
    }

    interface LoadPopularTvSeriesCallback {
        fun onPopularTvSeriesReceived(tvSeriesResponse: List<PopularTvSeries>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetail)
    }

    interface LoadTvSeriesDetailCallback {
        fun onTvSeriesDetailReceived(tvSeriesDetailResponse: TvSeriesDetail)
    }
}