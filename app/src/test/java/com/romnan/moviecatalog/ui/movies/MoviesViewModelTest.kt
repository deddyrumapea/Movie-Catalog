package com.romnan.moviecatalog.ui.movies

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun retrieveMovieData() {
        val movies = viewModel.retrieveMovieData()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}