package com.romnan.moviecatalog.core.domain.usecase

import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import com.romnan.moviecatalog.core.domain.repository.IMovieCatalogRepository
import kotlinx.coroutines.flow.Flow


class MovieCatalogInteractor(private val repository: IMovieCatalogRepository) :
    MovieCatalogUseCase {
    override fun getDiscoverMovies(): Flow<Resource<List<Movie>>> = repository.getDiscoverMovies()

    override fun getDiscoverTvSeries(): Flow<Resource<List<TvSeries>>> = repository.getDiscoverTvSeries()

    override fun getFavoriteMovies(): Flow<List<Movie>> = repository.getFavoriteMovies()

    override fun getFavoriteTvSeries(): Flow<List<TvSeries>> = repository.getFavoriteTvSeries()

    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> = repository.getMovieDetail(id)

    override fun getTvSeriesDetail(id: Int): Flow<Resource<TvSeries>> =
        repository.getTvSeriesDetail(id)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        repository.setFavoriteMovie(movie, state)

    override fun setFavoriteTvSeries(tvSeries: TvSeries, state: Boolean) =
        repository.setFavoriteTvSeries(tvSeries, state)
}