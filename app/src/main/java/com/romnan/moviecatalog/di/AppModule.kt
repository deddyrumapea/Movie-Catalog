package com.romnan.moviecatalog.di

import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogInteractor
import com.romnan.moviecatalog.core.domain.usecase.MovieCatalogUseCase
import com.romnan.moviecatalog.detail.movie.MovieDetailViewModel
import com.romnan.moviecatalog.detail.tvseries.TvSeriesDetailViewModel
import com.romnan.moviecatalog.discover.movie.DiscoverMovieViewModel
import com.romnan.moviecatalog.discover.tvseries.DiscoverTvSeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieCatalogUseCase> { MovieCatalogInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DiscoverMovieViewModel(get()) }
    viewModel { DiscoverTvSeriesViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvSeriesDetailViewModel(get()) }
}