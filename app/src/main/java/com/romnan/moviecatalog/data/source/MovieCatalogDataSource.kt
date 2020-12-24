package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries

interface MovieCatalogDataSource {

    fun getPopularMovies(): LiveData<PagedList<PopularMovie>>

    fun getPopularTvSeries(): LiveData<PagedList<PopularTvSeries>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun getTvSeriesDetail(tvSeriesId: Int): LiveData<TvSeriesDetail>

    fun insertFavoriteMovie(movie: MovieDetail)

    fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail)

    fun deleteFavoriteMovie(movie: MovieDetail)

    fun deleteFavoriteTvSeries(tvSeries: TvSeriesDetail)

    fun getFavoriteMovies(): LiveData<PagedList<MovieDetail>>

    fun getFavoriteTvSeries(): LiveData<PagedList<TvSeriesDetail>>

    fun isFavoriteMovie(movieId: Int): LiveData<Boolean>

    fun isFavoriteTvSeries(tvSeriesId: Int): LiveData<Boolean>
}