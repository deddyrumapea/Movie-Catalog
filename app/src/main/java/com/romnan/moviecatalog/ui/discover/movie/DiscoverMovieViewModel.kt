package com.romnan.moviecatalog.ui.discover.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverMovieViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<List<PopularMovie>> = repository.getPopularMovies()
}