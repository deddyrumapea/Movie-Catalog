package com.romnan.moviecatalog.ui.discover.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.movie.PopularMovie
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
class DiscoverMovieViewModelTest {

    private lateinit var viewModel: DiscoverMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<PopularMovie>>

    @Mock
    private lateinit var pagedList: PagedList<PopularMovie>

    @Before
    fun setUp() {
        viewModel = DiscoverMovieViewModel(repository)
    }

    @Test
    fun getPopularMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(20)
        val movies = MutableLiveData<PagedList<PopularMovie>>()
        movies.value = dummyMovies

        `when`(repository.getPopularMovies()).thenReturn(movies)
        val movieEntities = viewModel.getPopularMovies().value
        verify(repository).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getPopularMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}