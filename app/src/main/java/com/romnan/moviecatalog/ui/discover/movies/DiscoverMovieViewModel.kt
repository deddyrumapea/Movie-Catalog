package com.romnan.moviecatalog.ui.discover.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverMovieViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getPopularMovies(): LiveData<List<PopularShow>> = repository.getPopularMovies()
}