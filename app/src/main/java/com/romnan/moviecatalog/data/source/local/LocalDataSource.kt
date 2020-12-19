package com.romnan.moviecatalog.data.source.local

import androidx.lifecycle.LiveData
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val mFavoriteDao: FavoriteDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }

    fun insertFavoriteMovie(movie: MovieDetail) = mFavoriteDao.insertFavoriteMovie(movie)

    fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail) =
        mFavoriteDao.insertFavoriteTvSeries(tvSeries)

    fun deleteFavoriteMovie(movie: MovieDetail) = mFavoriteDao.deleteFavoriteMovie(movie)

    fun deleteFavoriteTvSeries(tvSeries: TvSeriesDetail) =
        mFavoriteDao.deleteFavoriteTvSeries(tvSeries)

    fun getFavoriteMovies(): LiveData<List<MovieDetail>> = mFavoriteDao.getFavoriteMovies()

    fun getFavoriteTvSeries(): LiveData<List<TvSeriesDetail>> = mFavoriteDao.getFavoriteTvSeries()

    fun isFavoriteMovie(movieId: Int): LiveData<Boolean> = mFavoriteDao.isFavoriteMovie(movieId)

    fun isFavoriteTvSeries(tvSeriesId: Int): LiveData<Boolean> =
        mFavoriteDao.isFavoriteTvSeries(tvSeriesId)
}