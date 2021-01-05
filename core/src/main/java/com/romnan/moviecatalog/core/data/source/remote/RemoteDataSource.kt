package com.romnan.moviecatalog.core.data.source.remote

import android.util.Log
import com.romnan.moviecatalog.core.data.source.remote.api.ApiService
import com.romnan.moviecatalog.core.data.source.remote.network.ApiResponse
import com.romnan.moviecatalog.core.data.source.remote.response.MovieResponse
import com.romnan.moviecatalog.core.data.source.remote.response.TvSeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        private const val TAG = "RemoteDataSource"
    }

    suspend fun getDiscoverMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getDiscoverMovies(1) // TODO : implement pagination
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e(TAG, "getDiscoverMovies: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDiscoverTvSeries(): Flow<ApiResponse<List<TvSeriesResponse>>> {
        return flow {
            try {
                val response = apiService.getDiscoverTvSeries(1)// TODO : implement pagination
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e(TAG, "getDiscoverTvSeries: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId)
                if (response.id != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getMovieDetail: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTvSeriesDetail(tvSeriesId: Int): Flow<ApiResponse<TvSeriesResponse>> {
        return flow {
            try {
                val response = apiService.getTvSeriesDetail(tvSeriesId)
                if (response.id != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getTvSeriesDetail: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}