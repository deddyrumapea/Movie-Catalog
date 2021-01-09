package com.romnan.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.core.presentation.movie.MovieBrief
import com.romnan.moviecatalog.core.util.MovieMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    private val _favoriteMovies = MutableLiveData<List<MovieBrief>>()
    val favoriteMovies: LiveData<List<MovieBrief>> = _favoriteMovies

    fun getFavoriteMovies() {
        viewModelScope.launch {
            useCase.getFavoriteMovies().collect { movies ->
                val data = movies.map { MovieMapper.domainToBrief(it) }
                _favoriteMovies.postValue(data)
            }
        }
    }
}