package com.romnan.moviecatalog.ui.movies

import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.model.Movie
import com.romnan.moviecatalog.util.DataDummy

class MoviesViewModel : ViewModel() {
    fun retrieveMovieData(): List<Movie> =
        DataDummy.generateDummyMovies()
}