package com.romnan.moviecatalog.detail.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase

class TvSeriesDetailViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {
    fun getTvSeriesDetail(tvShowId: Int): LiveData<Resource<TvSeries>> =
        useCase.getTvSeriesDetail(tvShowId).asLiveData()

    fun setFavoriteTvSeries(tvSeries: TvSeries, newState: Boolean) =
        useCase.setFavoriteTvSeries(tvSeries, newState)
}