package com.romnan.moviecatalog.ui.catalog

import com.romnan.moviecatalog.ui.catalog.tvshows.PopTvShowsViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PopTvShowsViewModelTestDetail {

    private lateinit var viewModelPop: PopTvShowsViewModel

    @Before
    fun setUp() {
        viewModelPop = PopTvShowsViewModel()
    }

    @Test
    fun retrieveTvShowData() {
        val tvShows = viewModelPop.retrieveTvShowData()
        assertNotNull(tvShows)
        assertEquals(11, tvShows.size)
    }
}