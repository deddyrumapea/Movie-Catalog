package com.romnan.moviecatalog.ui.tvshows

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun retrieveTvShowData() {
        val tvShows = viewModel.retrieveTvShowData()
        assertNotNull(tvShows)
        assertEquals(11, tvShows.size)
    }
}