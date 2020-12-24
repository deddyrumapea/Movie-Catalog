package com.romnan.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movie: MovieDetail)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail)

    @Delete
    fun deleteFavoriteMovie(movie: MovieDetail)

    @Delete
    fun deleteFavoriteTvSeries(tvSeries: TvSeriesDetail)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieDetail>

    @Query("SELECT * FROM favorite_tv_series")
    fun getFavoriteTvSeries(): DataSource.Factory<Int, TvSeriesDetail>

    @Query("SELECT EXISTS(SELECT * FROM favorite_movie WHERE id = :movieId)")
    fun isFavoriteMovie(movieId: Int): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT * FROM favorite_tv_series WHERE id = :tvSeriesId)")
    fun isFavoriteTvSeries(tvSeriesId: Int): LiveData<Boolean>
}