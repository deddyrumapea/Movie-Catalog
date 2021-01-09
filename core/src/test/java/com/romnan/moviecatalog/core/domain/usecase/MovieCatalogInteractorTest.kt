package com.romnan.moviecatalog.core.domain.usecase

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.romnan.moviecatalog.core.data.MovieCatalogRepository
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.movie.Movie
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieCatalogInteractorTest {

    private lateinit var interactor: MovieCatalogInteractor

    @Mock
    private lateinit var repository: MovieCatalogRepository

    private val dummyMovieId = 1
    private val dummyTvSeriesId = 1

    @Mock
    private lateinit var dummyMoviesList: Flow<List<Movie>>

    @Mock
    private lateinit var dummyMovieResource: Flow<Resource<Movie>>

    @Mock
    private lateinit var dummyMoviesListResource: Flow<Resource<List<Movie>>>

    @Mock
    private lateinit var dummyTvSeriesList: Flow<List<TvSeries>>

    @Mock
    private lateinit var dummyTvSeriesResource: Flow<Resource<TvSeries>>

    @Mock
    private lateinit var dummyTvSeriesListResource: Flow<Resource<List<TvSeries>>>


    @Before
    fun setUp() {
        interactor = MovieCatalogInteractor(repository)
        `when`(repository.getDiscoverMovies()).thenReturn(dummyMoviesListResource)
        `when`(repository.getDiscoverTvSeries()).thenReturn(dummyTvSeriesListResource)
        `when`(repository.getMovieDetail(dummyMovieId)).thenReturn(dummyMovieResource)
        `when`(repository.getTvSeriesDetail(dummyTvSeriesId)).thenReturn(dummyTvSeriesResource)
        `when`(repository.getFavoriteMovies()).thenReturn(dummyMoviesList)
        `when`(repository.getFavoriteTvSeries()).thenReturn(dummyTvSeriesList)
    }

    @Test
    fun getDiscoverMovies() {
        val result = interactor.getDiscoverMovies()
        verify(repository).getDiscoverMovies()
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun getDiscoverTvSeries() {
        val result = interactor.getDiscoverTvSeries()
        verify(repository).getDiscoverTvSeries()
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun getFavoriteMovies() {
        val result = interactor.getFavoriteMovies()
        verify(repository).getFavoriteMovies()
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun getFavoriteTvSeries() {
        val result = interactor.getFavoriteTvSeries()
        verify(repository).getFavoriteTvSeries()
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun getMovieDetail() {
        val result = interactor.getMovieDetail(dummyMovieId)
        verify(repository).getMovieDetail(dummyTvSeriesId)
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun getTvSeriesDetail() {
        val result = interactor.getTvSeriesDetail(dummyTvSeriesId)
        verify(repository).getTvSeriesDetail(dummyTvSeriesId)
        verifyNoMoreInteractions(repository)
        assertNotNull(result)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovie = mock(Movie::class.java)
        val dummyState = true
        interactor.setFavoriteMovie(dummyMovie, dummyState)
        verify(repository).setFavoriteMovie(dummyMovie, dummyState)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun setFavoriteTvSeries() {
        val dummyTvSeries = mock(TvSeries::class.java)
        val dummyState = true
        interactor.setFavoriteTvSeries(dummyTvSeries, dummyState)
        verify(repository).setFavoriteTvSeries(dummyTvSeries, dummyState)
        verifyNoMoreInteractions(repository)
    }
}