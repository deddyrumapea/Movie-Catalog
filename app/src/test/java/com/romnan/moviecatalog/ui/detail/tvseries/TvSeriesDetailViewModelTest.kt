package com.romnan.moviecatalog.ui.detail.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.romnan.moviecatalog.data.model.tvseries.TvSeriesDetail
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
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvSeriesDetailViewModelTest {
    private lateinit var viewModel: TvSeriesDetailViewModel
    private val dummyTvSeries = DummyGenerator.getPopularTvSeries()[0]
    private val dummyTvSeriesDetail = DummyGenerator.getTvSeriesDetail()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<TvSeriesDetail>

    @Before
    fun setUp() {
        viewModel = TvSeriesDetailViewModel(repository)
    }

    @Test
    fun getTvSeriesDetail() {
        val tvSeries = MutableLiveData<TvSeriesDetail>()
        tvSeries.value = dummyTvSeriesDetail

        `when`(repository.getTvSeriesDetail(dummyTvSeries.id)).thenReturn(tvSeries)
        val tvSeriesDetail = viewModel.getTvSeriesDetail(dummyTvSeries.id).value

        verify(repository).getTvSeriesDetail(dummyTvSeries.id)
        assertNotNull(tvSeriesDetail)
        assertEquals(dummyTvSeriesDetail, tvSeriesDetail)

        viewModel.getTvSeriesDetail(dummyTvSeries.id).observeForever(observer)
        verify(observer).onChanged(dummyTvSeriesDetail)
    }
}












