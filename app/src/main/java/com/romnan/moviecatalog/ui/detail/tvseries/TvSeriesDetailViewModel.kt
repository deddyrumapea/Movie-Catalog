package com.romnan.moviecatalog.ui.detail.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository

class TvSeriesDetailViewModel(private val repository: MovieCatalogRepository) : ViewModel() {
    companion object

    fun getTvSeriesDetail(tvShowId: Int): LiveData<TvSeriesDetail> =
        repository.getTvSeriesDetail(tvShowId)

    fun insertFavoriteTvSeries(tvSeries: TvSeriesDetail) = repository.insertFavoriteTvSeries(tvSeries)

    fun isFavoriteTvSeries(tvSeriesId: Int) = repository.isFavoriteTvSeries(tvSeriesId)
}