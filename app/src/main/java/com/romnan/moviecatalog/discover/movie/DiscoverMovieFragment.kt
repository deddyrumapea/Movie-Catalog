package com.romnan.moviecatalog.discover.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.adapter.MovieAdapter
import com.romnan.moviecatalog.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_discover_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverMovieFragment : Fragment() {

    private val viewModel: DiscoverMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_discover_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup adapter
        val moviesAdapter = MovieAdapter()
        moviesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, selected.id)
            startActivity(intent)
        }

        // Setup RecyclerView
        with(rv_discover_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        viewModel.getDiscoverMovies()
        viewModel.discoverMovies.observe(viewLifecycleOwner, { moviesAdapter.setData(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, { showProgressBar(it) })
        viewModel.errorMessage.observe(viewLifecycleOwner, { showErrorDialog(it) })
    }

    private fun showProgressBar(isLoading: Boolean) {
        pb_discover_movies.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String) {
        tv_error_discover_movie.visibility = View.VISIBLE
        tv_error_discover_movie.text = message
    }
}