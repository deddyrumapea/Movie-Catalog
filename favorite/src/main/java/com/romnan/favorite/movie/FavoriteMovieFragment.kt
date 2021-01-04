package com.romnan.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.favorite.R
import com.romnan.moviecatalog.core.adapter.MovieAdapter
import com.romnan.moviecatalog.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteMovieFragment : Fragment() {

    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favorite_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity == null) return

        val moviesAdapter = MovieAdapter()
        moviesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, selected.id)
            startActivity(intent)
        }

        progress_bar_favorite_movie.visibility = View.VISIBLE

        viewModel.favoriteMovies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                moviesAdapter.setData(movies)
                progress_bar_favorite_movie.visibility = View.GONE
            }
        })

        with(rv_favorite_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }
}