package com.romnan.moviecatalog.ui.detail.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class TvSeriesDetailViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getTvSeriesDetail(tvShowId: String): LiveData<TvSeriesDetail> =
        repository.getTvSeriesDetail(tvShowId)
}