package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.romnan.moviecatalog.data.model.MovieDetail
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.source.remote.RemoteDataSource

class FakeMovieCatalogRepository(private val remoteDataSource: RemoteDataSource) :
    MovieCatalogDataSource {

    override fun getPopularMovies(): LiveData<List<PopularShow>> {
        val result = MutableLiveData<List<PopularShow>>()

        remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
            override fun onPopularMoviesReceived(moviesResponse: List<PopularShow>) {
                result.postValue(moviesResponse)
            }
        })

        return result
    }

    override fun getPopularTvSeries(): LiveData<List<PopularShow>> {
        val result = MutableLiveData<List<PopularShow>>()

        remoteDataSource.getPopularTvSeries(object : RemoteDataSource.LoadPopularTvSeriesCallback {
            override fun onPopularTvSeriesReceived(tvSeriesResponse: List<PopularShow>) {
                result.postValue(tvSeriesResponse)
            }
        })

        return result
    }

    override fun getMovieDetail(movieId: String): LiveData<MovieDetail> {
        val result = MutableLiveData<MovieDetail>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetail) {
                result.postValue(movieDetailResponse)
            }
        })

        return result
    }

    override fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetail> {
        val result = MutableLiveData<TvSeriesDetail>()

        remoteDataSource.getTvSeriesDetail(
            tvSeriesId,
            object : RemoteDataSource.LoadTvSeriesDetailCallback {
                override fun onTvSeriesDetailReceived(tvSeriesDetailResponse: TvSeriesDetail) {
                    result.postValue(tvSeriesDetailResponse)
                }
            })

        return result
    }
}