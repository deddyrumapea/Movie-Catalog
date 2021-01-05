package com.romnan.favorite.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.core.presentation.TvSeriesPresentation
import com.romnan.moviecatalog.core.utils.TvSeriesMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FavoriteTvSeriesViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    private val _favoriteTvSeries = MutableLiveData<List<TvSeriesPresentation>>()
    val favoriteTvSeries: LiveData<List<TvSeriesPresentation>> = _favoriteTvSeries

    fun getFavoriteTvSeries() {
        viewModelScope.launch {
            useCase.getFavoriteTvSeries().collect { tvSeries ->
                val data = tvSeries.map { TvSeriesMapper.domainToPresentation(it) }
                _favoriteTvSeries.postValue(data)
            }
        }
    }
}