package com.romnan.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries

interface MovieCatalogDataSource {

    fun getPopularMovies(): LiveData<List<PopularMovie>>

    fun getPopularTvSeries(): LiveData<List<PopularTvSeries>>

    fun getMovieDetail(movieId: Int): LiveData<MovieDetail>

    fun getTvSeriesDetail(tvSeriesId: Int): LiveData<TvSeriesDetail>
}