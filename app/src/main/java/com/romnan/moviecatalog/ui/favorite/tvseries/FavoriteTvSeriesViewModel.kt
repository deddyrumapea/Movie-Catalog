package com.romnan.moviecatalog.ui.favorite.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class FavoriteTvSeriesViewModel(private val repository: MovieCatalogRepository) : ViewModel() {

    fun getFavoriteTvSeries(): LiveData<List<TvSeriesDetail>> = repository.getFavoriteTvSeries()
}