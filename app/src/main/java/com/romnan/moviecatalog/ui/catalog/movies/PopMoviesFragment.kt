package com.romnan.moviecatalog.ui.catalog.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import kotlinx.android.synthetic.main.fragment_popular_shows.*


class PopMoviesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_popular_shows, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PopMoviesViewModel::class.java]

        val movieAdapter = PopMoviesAdapter()

        with(rv_pop_show) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        viewModel.getPopMovies()

        viewModel.popMovies.observe(this, { movies ->
            movieAdapter.setShows(movies)
            Log.d(TAG, "onActivityCreated: ${movies.size}")
        })

        viewModel.isLoading.observe(this, {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    companion object {
        private const val TAG = "PopMoviesFragment"
    }
}