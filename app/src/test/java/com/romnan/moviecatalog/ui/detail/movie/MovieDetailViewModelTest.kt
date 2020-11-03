package com.romnan.moviecatalog.ui.detail.movie

import com.romnan.moviecatalog.util.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel()
        viewModel.setSelectedMovie(dummyMovie.id)
    }

    @Test
    fun getMovie() {
        val movieItem = viewModel.getMovie()
        assertNotNull(movieItem)

        assertEquals(dummyMovie.id, movieItem.id)
        assertEquals(dummyMovie.poster, movieItem.poster)
        assertEquals(dummyMovie.title, movieItem.title)
        assertEquals(dummyMovie.trailerUrl, movieItem.trailerUrl)
        assertEquals(dummyMovie.releaseDate, movieItem.releaseDate)
        assertEquals(dummyMovie.genre, movieItem.genre)
        assertEquals(dummyMovie.duration, movieItem.duration)
        assertEquals(dummyMovie.score, movieItem.score)
        assertEquals(dummyMovie.tagline, movieItem.tagline)
        assertEquals(dummyMovie.overview, movieItem.overview)
        assertEquals(dummyMovie.director, movieItem.director)
        assertEquals(dummyMovie.casts, movieItem.casts)
        assertEquals(dummyMovie.status, movieItem.status)
        assertEquals(dummyMovie.language, movieItem.language)
        assertEquals(dummyMovie.budget, movieItem.budget)
        assertEquals(dummyMovie.revenue, movieItem.revenue)
        assertEquals(dummyMovie.keyword, movieItem.keyword)
    }
}






















