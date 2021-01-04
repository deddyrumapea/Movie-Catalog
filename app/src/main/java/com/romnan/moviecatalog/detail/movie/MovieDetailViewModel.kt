package com.romnan.moviecatalog.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase


class MovieDetailViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>> =
        useCase.getMovieDetail(movieId).asLiveData()

    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        useCase.setFavoriteMovie(movie, newState)
}