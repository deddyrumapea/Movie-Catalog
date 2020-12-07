package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.romnan.moviecatalog.data.model.MovieDetail
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.model.TvSeriesDetail

interface MovieCatalogDataSource {

    fun getPopularMovies(): LiveData<List<PopularShow>>

    fun getPopularTvSeries(): LiveData<List<PopularShow>>

    fun getMovieDetail(movieId: String): LiveData<MovieDetail>

    fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetail>
}