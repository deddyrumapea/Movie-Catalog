package com.romnan.moviecatalog.core.data.source.local

import com.romnan.moviecatalog.core.data.source.local.entity.MovieEntity
import com.romnan.moviecatalog.core.data.source.local.entity.TvSeriesEntity
import com.romnan.moviecatalog.core.data.source.local.room.MovieCatalogDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieCatalogDao: MovieCatalogDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieCatalogDao.getAllMovies()

    fun getAllTvSeries(): Flow<List<TvSeriesEntity>> = movieCatalogDao.getAllTvSeries()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieCatalogDao.getFavoriteMovies()

    fun getFavoriteTvSeries(): Flow<List<TvSeriesEntity>> =
        movieCatalogDao.getFavoriteTvSeries()

    fun getMovieDetail(movieId: Int): Flow<MovieEntity> =
        movieCatalogDao.getMovieDetail(movieId)

    fun getTvSeriesDetail(tvSeriesId: Int): Flow<TvSeriesEntity> =
        movieCatalogDao.getTvSeriesDetail(tvSeriesId)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieCatalogDao.insertMovies(movies)

    suspend fun insertTvSeries(tvSeries: List<TvSeriesEntity>) =
        movieCatalogDao.insertTvSeries(tvSeries)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieCatalogDao.updateFavoriteMovies(movie)
    }

    fun setFavoriteTvSeries(tvSeries: TvSeriesEntity, newState: Boolean) {
        tvSeries.isFavorite = newState
        movieCatalogDao.updateFavoriteTvSeries(tvSeries)
    }

}