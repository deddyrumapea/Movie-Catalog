package com.romnan.moviecatalog.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class MovieDetailViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail> = repository.getMovieDetail(movieId)

    fun insertFavoriteMovie(movie: MovieDetail) = repository.insertFavoriteMovie(movie)

    fun deleteFavoriteMovie(movie: MovieDetail) = repository.deleteFavoriteMovie(movie)

    fun isFavoriteMovie(movieId: Int) = repository.isFavoriteMovie(movieId)
}