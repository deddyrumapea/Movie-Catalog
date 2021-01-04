package com.romnan.favorite.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase

class FavoriteTvSeriesViewModel(useCase: MovieCatalogUseCase) : ViewModel() {

    val favoriteTvSeries = useCase.getFavoriteTvSeries().asLiveData()
}