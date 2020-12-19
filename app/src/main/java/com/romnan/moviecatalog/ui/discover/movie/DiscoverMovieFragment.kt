package com.romnan.moviecatalog.ui.discover.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_discover_movie.*


class DiscoverMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_discover_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[DiscoverMovieViewModel::class.java]

            val moviesAdapter = DiscoverMovieAdapter()

            progress_bar_popular_movies.visibility = View.VISIBLE

            viewModel.getPopularMovies().observe(viewLifecycleOwner, { movies ->
                moviesAdapter.setMovies(movies)
                progress_bar_popular_movies.visibility = View.GONE
                Log.d(TAG, "onActivityCreated: ${movies.size}")
            })

            with(rv_popular_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    companion object {
        private const val TAG = "DiscoverMovieFragment"
    }
}