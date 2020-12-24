package com.romnan.moviecatalog.ui.favorite.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
import com.romnan.moviecatalog.data.source.MovieCatalogRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvSeriesViewModelTest {

    private lateinit var viewModel: FavoriteTvSeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvSeriesDetail>>

    @Mock
    private lateinit var pagedList: PagedList<TvSeriesDetail>

    @Before
    fun setUp() {
        viewModel = FavoriteTvSeriesViewModel(repository)
    }

    @Test
    fun getFavoriteTvSeries() {
        val dummyTvSeries = pagedList
        `when`(dummyTvSeries.size).thenReturn(20)
        val tvSeries = MutableLiveData<PagedList<TvSeriesDetail>>()
        tvSeries.value = dummyTvSeries

        `when`(repository.getFavoriteTvSeries()).thenReturn(tvSeries)
        val tvSeriesEntities = viewModel.getFavoriteTvSeries().value
        verify(repository).getFavoriteTvSeries()
        assertNotNull(tvSeriesEntities)
        assertEquals(20, tvSeriesEntities?.size)

        viewModel.getFavoriteTvSeries().observeForever(observer)
        verify(observer).onChanged(dummyTvSeries)
    }
}