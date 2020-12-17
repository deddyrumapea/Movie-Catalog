package com.romnan.moviecatalog.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class MovieDetailViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail> = repository.getMovieDetail(movieId)
}