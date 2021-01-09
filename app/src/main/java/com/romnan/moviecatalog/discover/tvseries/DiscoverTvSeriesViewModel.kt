package com.romnan.moviecatalog.discover.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.core.presentation.tvseries.TvSeriesBrief
import com.romnan.moviecatalog.core.util.TvSeriesMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiscoverTvSeriesViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    private val _discoverTvSeries = MutableLiveData<List<TvSeriesBrief>>()
    val discoverTvSeries: LiveData<List<TvSeriesBrief>> = _discoverTvSeries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getDiscoverTvSeries() {
        viewModelScope.launch {
            useCase.getDiscoverTvSeries().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _isLoading.postValue(true)
                    is Resource.Success -> {
                        val data = resource.data?.let { tvSeries ->
                            tvSeries.map { TvSeriesMapper.domainToBrief(it) }
                        }
                        _discoverTvSeries.postValue(data)
                        _isLoading.postValue(false)
                    }
                    is Resource.Error -> {
                        _errorMessage.postValue(resource.message)
                        _isLoading.postValue(false)
                    }
                }
            }
        }
    }
}