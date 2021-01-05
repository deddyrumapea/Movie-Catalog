package com.romnan.moviecatalog.discover.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase

class DiscoverMovieViewModel(useCase: MovieCatalogUseCase) : ViewModel() {

    val discoverMovies = useCase.getDiscoverMovies().asLiveData()
}