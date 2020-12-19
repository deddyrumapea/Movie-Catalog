package com.romnan.moviecatalog.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite_movie.*


class FavoriteMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favorite_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[FavoriteMovieViewModel::class.java]

            val moviesAdapter = FavoriteMovieAdapter()

            progress_bar_popular_movies.visibility = View.VISIBLE

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                moviesAdapter.setMovies(movies)
                progress_bar_popular_movies.visibility = View.GONE
            })

            with(rv_popular_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }
}