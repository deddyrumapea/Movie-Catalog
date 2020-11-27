package com.romnan.moviecatalog.ui.catalog

import com.romnan.moviecatalog.ui.catalog.movies.PopMoviesViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PopMoviesViewModelTest {

    private lateinit var viewModelPop: PopMoviesViewModel

    @Before
    fun setUp() {
        viewModelPop = PopMoviesViewModel()
    }

    @Test
    fun retrieveMovieData() {
        val movies = viewModelPop.retrieveMovieData()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}