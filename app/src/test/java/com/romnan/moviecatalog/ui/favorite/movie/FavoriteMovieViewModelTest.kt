package com.romnan.moviecatalog.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.romnan.moviecatalog.data.model.movie.MovieDetail
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
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieDetail>>

    @Mock
    private lateinit var pagedList: PagedList<MovieDetail>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(20)
        val movies = MutableLiveData<PagedList<MovieDetail>>()
        movies.value = dummyMovies

        `when`(repository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}