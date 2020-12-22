package com.romnan.moviecatalog.ui.discover.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverMovieViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    fun getPopularMovies(): LiveData<PagedList<PopularMovie>> =
        LivePagedListBuilder(repository.getPopularMovies(), 20).build()
}