package com.romnan.favorite.di

import com.romnan.favorite.movie.FavoriteMovieViewModel
import com.romnan.favorite.tvseries.FavoriteTvSeriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvSeriesViewModel(get()) }
}