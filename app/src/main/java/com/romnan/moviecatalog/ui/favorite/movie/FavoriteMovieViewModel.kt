package com.romnan.moviecatalog.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class FavoriteMovieViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<MovieDetail>> =
        LivePagedListBuilder(repository.getFavoriteMovies(), 20).build()
}