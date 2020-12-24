package com.romnan.moviecatalog.ui.favorite.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class FavoriteTvSeriesViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    fun getFavoriteTvSeries(): LiveData<PagedList<TvSeriesDetail>> =
        repository.getFavoriteTvSeries()
}