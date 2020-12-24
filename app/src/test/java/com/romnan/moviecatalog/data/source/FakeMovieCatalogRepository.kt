package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.source.local.LocalDataSource
import com.romnan.moviecatalog.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.utils.AppExecutors


class FakeMovieCatalogRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogDataSource {

    companion object;


    override fun getPopularMovies(): LiveData<PagedList<PopularMovie>> =
        LivePagedListBuilder(remoteDataSource.getPopularMovies(), 20).build()


    override fun getPopularTvSeries(): LiveData<PagedList<PopularTvSeries>> =
        LivePagedListBuilder(remoteDataSource.getPopularTvSeries(), 20).build()

    override fun getMovieDetail(movieId: Int): LiveData<MovieDetail> {
        val result = MutableLiveData<MovieDetail>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: MovieDetail) {
                result.postValue(movieDetailResponse)
            }
        })

        return result
    }

    override fun getTvSeriesDetail(tvSeriesId: Int): LiveData<TvSeriesDetail> {
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

    override fun insertFavoriteMovie(movie: MovieDetail) =
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteMovie(movie)
        }

    override fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail) =
        appExecutors.diskIO().execute {
            localDataSource.insertFavoriteTvSeries(tvSeries)
        }

    override fun deleteFavoriteMovie(movie: MovieDetail) =
        appExecutors.diskIO().execute {
            localDataSource.deleteFavoriteMovie(movie)
        }

    override fun deleteFavoriteTvSeries(tvSeries: TvSeriesDetail) =
        appExecutors.diskIO().execute {
            localDataSource.deleteFavoriteTvSeries(tvSeries)
        }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieDetail>> {
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), 20).build()
    }

    override fun getFavoriteTvSeries(): LiveData<PagedList<TvSeriesDetail>> {
        return LivePagedListBuilder(localDataSource.getFavoriteTvSeries(), 20).build()
    }

    override fun isFavoriteMovie(movieId: Int): LiveData<Boolean> =
        localDataSource.isFavoriteMovie(movieId)

    override fun isFavoriteTvSeries(tvSeriesId: Int): LiveData<Boolean> =
        localDataSource.isFavoriteTvSeries(tvSeriesId)
}