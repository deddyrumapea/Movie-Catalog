package com.romnan.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.core.presentation.MoviePresentation
import com.romnan.moviecatalog.core.utils.MovieMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    private val _favoriteMovies = MutableLiveData<List<MoviePresentation>>()
    val favoriteMovies: LiveData<List<MoviePresentation>> = _favoriteMovies

    fun getFavoriteMovies() {
        viewModelScope.launch {
            useCase.getFavoriteMovies().collect { movies ->
                val data = movies.map { MovieMapper.domainToPresentation(it) }
                _favoriteMovies.postValue(data)
            }
        }
    }
}