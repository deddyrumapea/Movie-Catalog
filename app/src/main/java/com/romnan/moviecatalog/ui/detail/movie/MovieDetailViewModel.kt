package com.romnan.moviecatalog.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.model.Movie
import com.romnan.moviecatalog.util.DataDummy

class MovieDetailViewModel : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): Movie {
        lateinit var movie: Movie
        val movies = DataDummy.generateDummyMovies()

        // Search for the corresponding movie
        for (movieItem in movies) {
            if (movieItem.id == movieId) {
                movie = movieItem
            }
        }
        return movie
    }
}