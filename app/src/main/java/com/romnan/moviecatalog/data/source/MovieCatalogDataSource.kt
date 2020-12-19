package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries

interface MovieCatalogDataSource {

    fun getPopularMovies(): LiveData<List<PopularMovie>>

    fun getPopularTvSeries(): LiveData<List<PopularTvSeries>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun getTvSeriesDetail(tvSeriesId: Int): LiveData<TvSeriesDetail>

    fun insertFavoriteMovie(movie: MovieDetail)

    fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail)

    fun deleteFavoriteMovie(movie: MovieDetail)

    fun deleteFavoriteTvSeries(tvSeries: TvSeriesDetail)

    fun getFavoriteMovies(): LiveData<List<MovieDetail>>

    fun getFavoriteTvSeries(): LiveData<List<TvSeriesDetail>>

    fun isFavoriteMovie(movieId: Int): LiveData<Boolean>

    fun isFavoriteTvSeries(tvSeriesId: Int): LiveData<Boolean>
}