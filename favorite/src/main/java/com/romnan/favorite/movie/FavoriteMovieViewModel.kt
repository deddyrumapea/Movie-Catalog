package com.romnan.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase

class FavoriteMovieViewModel(useCase: MovieCatalogUseCase) : ViewModel() {

    val favoriteMovies = useCase.getFavoriteMovies().asLiveData()
}