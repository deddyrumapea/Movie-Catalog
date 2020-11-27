package com.romnan.moviecatalog.ui.detail.tvshows

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowDetailDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel()
        viewModel.setSelectedTvShow(dummyTvShow.id)
    }

    @Test
    fun getTvShow() {
        val tvShowItem = viewModel.getTvShow()
        assertNotNull(tvShowItem)

        assertEquals(dummyTvShow.id, tvShowItem.id)
        assertEquals(dummyTvShow.poster, tvShowItem.poster)
        assertEquals(dummyTvShow.title, tvShowItem.title)
        assertEquals(dummyTvShow.trailerUrl, tvShowItem.trailerUrl)
        assertEquals(dummyTvShow.genre, tvShowItem.genre)
        assertEquals(dummyTvShow.duration, tvShowItem.duration)
        assertEquals(dummyTvShow.score, tvShowItem.score)
        assertEquals(dummyTvShow.tagline, tvShowItem.tagline)
        assertEquals(dummyTvShow.overview, tvShowItem.overview)
        assertEquals(dummyTvShow.creator, tvShowItem.creator)
        assertEquals(dummyTvShow.casts, tvShowItem.casts)
        assertEquals(dummyTvShow.status, tvShowItem.status)
        assertEquals(dummyTvShow.type, tvShowItem.type)
        assertEquals(dummyTvShow.language, tvShowItem.language)
        assertEquals(dummyTvShow.keyword, tvShowItem.keyword)
        assertEquals(dummyTvShow.lastSeason, tvShowItem.lastSeason)

    }
}