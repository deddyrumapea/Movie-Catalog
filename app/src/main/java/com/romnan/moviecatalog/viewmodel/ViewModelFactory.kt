package com.romnan.moviecatalog.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.romnan.moviecatalog.data.source.MovieCatalogRepository
import com.romnan.moviecatalog.data.source.local.LocalDataSource
import com.romnan.moviecatalog.data.source.local.room.MovieCatalogDatabase
import com.romnan.moviecatalog.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailViewModel
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailViewModel
import com.romnan.moviecatalog.ui.discover.movie.DiscoverMovieViewModel
import com.romnan.moviecatalog.ui.discover.tvseries.DiscoverTvSeriesViewModel
import com.romnan.moviecatalog.ui.favorite.movie.FavoriteMovieViewModel
import com.romnan.moviecatalog.ui.favorite.tvseries.FavoriteTvSeriesViewModel
import com.romnan.moviecatalog.utils.AppExecutors

class ViewModelFactory private constructor(private val repository: MovieCatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(
                        MovieCatalogRepository.getInstance(
                            RemoteDataSource.getInstance(),
                            LocalDataSource.getInstance(
                                MovieCatalogDatabase.getInstance(context).favoriteDao()
                            ),
                            AppExecutors()
                        )
                    )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DiscoverMovieViewModel::class.java) -> {
                return DiscoverMovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DiscoverTvSeriesViewModel::class.java) -> {
                return DiscoverTvSeriesViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                return MovieDetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(TvSeriesDetailViewModel::class.java) -> {
                return TvSeriesDetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(repository) as T
            }

            modelClass.isAssignableFrom(FavoriteTvSeriesViewModel::class.java) -> {
                return FavoriteTvSeriesViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}