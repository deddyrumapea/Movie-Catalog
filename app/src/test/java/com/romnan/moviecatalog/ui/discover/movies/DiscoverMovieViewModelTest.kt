package com.romnan.moviecatalog.ui.discover.movies

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
class DiscoverMovieViewModelTest {

    private lateinit var viewModel: DiscoverMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<List<PopularShow>>

    @Before
    fun setUp() {
        viewModel = DiscoverMovieViewModel(repository)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovies = DummyGenerator.getPopularMovies()
        val movies = MutableLiveData<List<PopularShow>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovies()).thenReturn(movies)
        val moviesList = viewModel.getPopularMovies().value
        verify(repository).getPopularMovies()
        assertNotNull(movies)
        assertEquals(20, moviesList?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}