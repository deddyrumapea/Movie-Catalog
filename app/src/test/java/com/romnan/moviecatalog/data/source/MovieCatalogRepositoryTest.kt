package com.romnan.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.utils.DummyGenerator
import com.romnan.moviecatalog.utils.LiveDataTestUtil
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieCatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeMovieCatalogRepository(remote)

    private val dummyPopularMovies = DummyGenerator.getPopularMovies()
    private val dummyMovieDetail = DummyGenerator.getMovieDetail()
    private val movieId = dummyPopularMovies[0].id

    private val dummyPopularTvSeries = DummyGenerator.getPopularTvSeries()
    private val dummyTvSeriesDetail = DummyGenerator.getTvSeriesDetail()
    private val tvSeriesId = dummyPopularTvSeries[0].id

    @Test
    fun getPopularMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback)
                .onPopularMoviesReceived(dummyPopularMovies)
            null
        }.`when`(remote).getPopularMovies(any())
        val popularMovies = LiveDataTestUtil.getValue(repository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        assertNotNull(popularMovies)
        assertEquals(dummyPopularMovies, popularMovies)
    }

    @Test
    fun getPopularTvSeries() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadPopularTvSeriesCallback)
                .onPopularTvSeriesReceived(dummyPopularTvSeries)
            null
        }.`when`(remote).getPopularTvSeries(any())
        val popularTvSeries = LiveDataTestUtil.getValue(repository.getPopularTvSeries())
        verify(remote).getPopularTvSeries(any())
        assertNotNull(popularTvSeries)
        assertEquals(dummyPopularTvSeries, popularTvSeries)
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