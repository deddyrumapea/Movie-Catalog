package com.romnan.moviecatalog.data.source.remote

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.movie.PopularMovieResponse
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeriesResponse
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.source.remote.api.ApiConfig
import com.romnan.moviecatalog.data.source.remote.api.ApiService
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

    fun getPopularMovies(): DataSource.Factory<Int, PopularMovie> =
        object : DataSource.Factory<Int, PopularMovie>() {
            override fun create(): DataSource<Int, PopularMovie> {
                return object : PageKeyedDataSource<Int, PopularMovie>() {
                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, PopularMovie>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client =
                            ApiConfig.getApiService().getPopularMovies(ApiService.API_KEY, 1)

                        client.enqueue(object : Callback<PopularMovieResponse> {
                            override fun onResponse(
                                call: Call<PopularMovieResponse>,
                                response: Response<PopularMovieResponse>
                            ) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    callback.onResult(responseBody.results, null, 2)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                        })
                    }

                    override fun loadBefore(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularMovie>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client = ApiConfig.getApiService()
                            .getPopularMovies(ApiService.API_KEY, params.key)

                        client.enqueue(object : Callback<PopularMovieResponse> {
                            override fun onResponse(
                                call: Call<PopularMovieResponse>,
                                response: Response<PopularMovieResponse>
                            ) {
                                val key = if (params.key > 1) params.key - 1 else null
                                val responseBody = response.body()

                                if (responseBody != null) {
                                    callback.onResult(responseBody.results, key)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }
                        })
                    }

                    override fun loadAfter(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularMovie>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client = ApiConfig.getApiService()
                            .getPopularMovies(ApiService.API_KEY, params.key)

                        client.enqueue(object : Callback<PopularMovieResponse> {
                            override fun onResponse(
                                call: Call<PopularMovieResponse>,
                                response: Response<PopularMovieResponse>
                            ) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    val hasMore =
                                        responseBody.page < responseBody.totalPages
                                    val key = if (hasMore) params.key + 1 else null
                                    callback.onResult(responseBody.results, key)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }
                        })
                    }
                }
            }
        }

    fun getPopularTvSeries(): DataSource.Factory<Int, PopularTvSeries> =
        object : DataSource.Factory<Int, PopularTvSeries>() {
            override fun create(): DataSource<Int, PopularTvSeries> {
                return object : PageKeyedDataSource<Int, PopularTvSeries>() {
                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, PopularTvSeries>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client =
                            ApiConfig.getApiService().getPopularTvSeries(ApiService.API_KEY, 1)

                        client.enqueue(object : Callback<PopularTvSeriesResponse> {
                            override fun onResponse(
                                call: Call<PopularTvSeriesResponse>,
                                response: Response<PopularTvSeriesResponse>
                            ) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    callback.onResult(responseBody.results, null, 2)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(
                                call: Call<PopularTvSeriesResponse>,
                                t: Throwable
                            ) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                        })
                    }

                    override fun loadBefore(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularTvSeries>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client = ApiConfig.getApiService()
                            .getPopularTvSeries(ApiService.API_KEY, params.key)

                        client.enqueue(object : Callback<PopularTvSeriesResponse> {
                            override fun onResponse(
                                call: Call<PopularTvSeriesResponse>,
                                response: Response<PopularTvSeriesResponse>
                            ) {
                                val key = if (params.key > 1) params.key - 1 else null

                                val responseBody = response.body()
                                if (responseBody != null) {
                                    callback.onResult(responseBody.results, key)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(
                                call: Call<PopularTvSeriesResponse>,
                                t: Throwable
                            ) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                        })
                    }

                    override fun loadAfter(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, PopularTvSeries>
                    ) {
                        EspressoIdlingResource.increment() //TODO: delete

                        val client = ApiConfig.getApiService()
                            .getPopularTvSeries(ApiService.API_KEY, params.key)

                        client.enqueue(object : Callback<PopularTvSeriesResponse> {
                            override fun onResponse(
                                call: Call<PopularTvSeriesResponse>,
                                response: Response<PopularTvSeriesResponse>
                            ) {
                                val responseBody = response.body()
                                if (responseBody != null) {
                                    val hasMore =
                                        responseBody.page < responseBody.totalPages

                                    val key = if (hasMore) params.key + 1 else null
                                    callback.onResult(responseBody.results, key)
                                }
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                            override fun onFailure(
                                call: Call<PopularTvSeriesResponse>,
                                t: Throwable
                            ) {
                                Log.e(TAG, "onFailure: ${t.message.toString()}")
                                EspressoIdlingResource.decrement() //TODO : delete
                            }

                        })
                    }

                }
            }

        }

    fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment() //TODO: delete

        val client =
            ApiConfig.getApiService().getMovieDetail(movieId, ApiService.API_KEY)


        client.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onMovieDetailReceived(responseBody)
                    }
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
            .getTvSeriesDetail(tvShowId, ApiService.API_KEY)

        client.enqueue(object : Callback<TvSeriesDetail> {
            override fun onResponse(
                call: Call<TvSeriesDetail>,
                response: Response<TvSeriesDetail>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        callback.onTvSeriesDetailReceived(responseBody)
                    }
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

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieDetail)
    }

    interface LoadTvSeriesDetailCallback {
        fun onTvSeriesDetailReceived(tvSeriesDetailResponse: TvSeriesDetail)
    }
}