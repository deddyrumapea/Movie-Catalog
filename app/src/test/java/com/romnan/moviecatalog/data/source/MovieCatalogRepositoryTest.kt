package com.romnan.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.data.source.local.LocalDataSource
import com.romnan.moviecatalog.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.utils.AppExecutors
import com.romnan.moviecatalog.utils.DummyGenerator
import com.romnan.moviecatalog.utils.LiveDataTestUtil
import com.romnan.moviecatalog.utils.PagedListUtil
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeMovieCatalogRepository(remote, local, appExecutors)

    private val dummyPopularMovies = DummyGenerator.getPopularMovies()
    private val dummyMovieDetail = DummyGenerator.getMovieDetail()
    private val movieId = dummyPopularMovies[0].id

    private val dummyPopularTvSeries = DummyGenerator.getPopularTvSeries()
    private val dummyTvSeriesDetail = DummyGenerator.getTvSeriesDetail()
    private val tvSeriesId = dummyPopularTvSeries[0].id

    @Test
    fun getPopularMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, PopularMovie>

        `when`(remote.getPopularMovies()).thenReturn(dataSourceFactory)
        repository.getPopularMovies()

        val moviesList = PagedListUtil.mockPagedList(DummyGenerator.getPopularMovies())
        verify(remote).getPopularMovies()
        assertNotNull(moviesList)
        assertEquals(dummyPopularTvSeries.size.toLong(), moviesList.size.toLong())
    }

    @Test
    fun getPopularTvSeries() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, PopularTvSeries>

        `when`(remote.getPopularTvSeries()).thenReturn(dataSourceFactory)
        repository.getPopularTvSeries()

        val tvSeriesList = PagedListUtil.mockPagedList(DummyGenerator.getPopularTvSeries())
        verify(remote).getPopularTvSeries()
        assertNotNull(tvSeriesList)
        assertEquals(dummyPopularTvSeries.size.toLong(), tvSeriesList.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                .onMovieDetailReceived(dummyMovieDetail)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), any())

        val movieDetail = LiveDataTestUtil.getValue(repository.getMovieDetail(movieId))
        verify(remote).getMovieDetail(eq(movieId), any())
        assertNotNull(movieDetail)
        assertEquals(dummyMovieDetail, movieDetail)
    }

    @Test
    fun getTvSeriesDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.LoadTvSeriesDetailCallback)
                .onTvSeriesDetailReceived(dummyTvSeriesDetail)
            null
        }.`when`(remote).getTvSeriesDetail(eq(tvSeriesId), any())

        val tvSeriesDetail = LiveDataTestUtil.getValue(repository.getTvSeriesDetail(tvSeriesId))
        verify(remote).getTvSeriesDetail(eq(tvSeriesId), any())
        assertNotNull(tvSeriesDetail)
        assertEquals(dummyTvSeriesDetail, tvSeriesDetail)
    }
}