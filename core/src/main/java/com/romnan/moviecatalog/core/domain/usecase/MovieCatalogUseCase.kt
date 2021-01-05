package com.romnan.moviecatalog.core.domain.usecase

import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import kotlinx.coroutines.flow.Flow

interface MovieCatalogUseCase {
    fun getDiscoverMovies(): Flow<Resource<List<Movie>>>

    fun getDiscoverTvSeries(): Flow<Resource<List<TvSeries>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getFavoriteTvSeries(): Flow<List<TvSeries>>

    fun getMovieDetail(id: Int): Flow<Resource<Movie>>

    fun getTvSeriesDetail(id: Int): Flow<Resource<TvSeries>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTvSeries(tvSeries: TvSeries, state: Boolean)
}