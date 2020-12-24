
package com.romnan.moviecatalog.ui.discover.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverTvSeriesViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getPopularTvSeries(): LiveData<PagedList<PopularTvSeries>> = repository.getPopularTvSeries()
}