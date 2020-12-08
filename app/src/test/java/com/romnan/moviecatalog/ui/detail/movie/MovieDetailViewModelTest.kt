package com.romnan.moviecatalog.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.MovieDetail
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
class MovieDetailViewModelTest {
    private lateinit var viewModel: MovieDetailViewModel
    private val dummyMovie = DummyGenerator.getPopularMovies()[0]
    private val dummyMovieDetail = DummyGenerator.getMovieDetail()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<MovieDetail>


    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieDetail>()
        movie.value = dummyMovieDetail

        `when`(repository.getMovieDetail(dummyMovie.id)).thenReturn(movie)
        val movieDetail = viewModel.getMovieDetail(dummyMovie.id).value

        verify(repository).getMovieDetail(dummyMovie.id)
        assertNotNull(movieDetail)
        assertEquals(dummyMovieDetail, movieDetail)

        viewModel.getMovieDetail(dummyMovie.id).observeForever(observer)
        verify(observer).onChanged(dummyMovieDetail)
    }
}