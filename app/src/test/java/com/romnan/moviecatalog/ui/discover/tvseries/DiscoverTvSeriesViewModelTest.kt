package com.romnan.moviecatalog.ui.discover.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.PopularShow
import com.romnan.moviecatalog.data.source.MovieCatalogRepository
import com.romnan.moviecatalog.utils.DummyGenerator
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
class DiscoverTvSeriesViewModelTest {

    private lateinit var viewModel: DiscoverTvSeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<List<PopularShow>>

    @Before
    fun setUp() {
        viewModel = DiscoverTvSeriesViewModel(repository)
    }

    @Test
    fun getPopularTvSeries() {
        val dummyTvSeries = DummyGenerator.getPopularTvSeries()
        val tvSeries = MutableLiveData<List<PopularShow>>()
        tvSeries.value = dummyTvSeries

        `when`(repository.getPopularTvSeries()).thenReturn(tvSeries)
        val tvSeriesList = viewModel.getPopularTvSeries().value
        assertNotNull(tvSeriesList)
        assertEquals(20, tvSeriesList?.size)

        viewModel.getPopularTvSeries().observeForever(observer)
        verify(observer).onChanged(dummyTvSeries)
    }
}