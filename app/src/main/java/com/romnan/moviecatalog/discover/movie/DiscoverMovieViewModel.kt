package com.romnan.moviecatalog.discover.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.core.presentation.MoviePresentation
import com.romnan.moviecatalog.core.utils.MovieMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiscoverMovieViewModel(private val useCase: MovieCatalogUseCase) : ViewModel() {

    private val _discoverMovies = MutableLiveData<List<MoviePresentation>>()
    val discoverMovies: LiveData<List<MoviePresentation>> = _discoverMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getDiscoverMovies() {
        viewModelScope.launch {
            useCase.getDiscoverMovies().collect { resource ->
                when (resource) {
                    is Resource.Loading -> _isLoading.postValue(true)
                    is Resource.Success -> {
                        val data = resource.data?.let { movies ->
                            movies.map { MovieMapper.domainToPresentation(it) }
                        }
                        _discoverMovies.postValue(data)
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