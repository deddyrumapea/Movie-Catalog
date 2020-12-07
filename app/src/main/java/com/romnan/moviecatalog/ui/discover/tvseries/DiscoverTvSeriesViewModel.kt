package com.romnan.moviecatalog.ui.discover.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverTvSeriesViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getPopularTvSeries(): LiveData<List<PopularShow>> = repository.getPopularTvSeries()
}