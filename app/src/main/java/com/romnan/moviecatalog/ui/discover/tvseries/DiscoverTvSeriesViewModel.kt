package com.romnan.moviecatalog.ui.discover.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class DiscoverTvSeriesViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getPopularTvSeries(): LiveData<PagedList<PopularTvSeries>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        return LivePagedListBuilder(repository.getPopularTvSeries(), config).build()
    }
}