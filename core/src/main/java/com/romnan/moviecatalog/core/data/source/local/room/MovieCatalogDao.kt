package com.romnan.moviecatalog.core.data.source.local.room

import androidx.room.*
import com.romnan.moviecatalog.core.data.source.local.entity.MovieEntity
import com.romnan.moviecatalog.core.data.source.local.entity.TvSeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCatalogDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_series")
    fun getAllTvSeries(): Flow<List<TvSeriesEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv_series WHERE isFavorite = 1")
    fun getFavoriteTvSeries(): Flow<List<TvSeriesEntity>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieEntity>

    @Query("SELECT * FROM tv_series WHERE id= :tvSeriesId")
    fun getTvSeriesDetail(tvSeriesId: Int): Flow<TvSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tvSeries: List<TvSeriesEntity>)

    @Update
    fun updateFavoriteMovies(movie: MovieEntity)

    @Update
    fun updateFavoriteTvSeries(tvSeries: TvSeriesEntity)
}