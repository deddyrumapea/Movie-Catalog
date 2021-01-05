package com.romnan.moviecatalog.discover.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase

class DiscoverTvSeriesViewModel(useCase: MovieCatalogUseCase) : ViewModel() {

    val discoverTvSeries = useCase.getDiscoverTvSeries().asLiveData()
}