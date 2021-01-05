package com.romnan.moviecatalog.discover.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.adapter.MovieAdapter
import com.romnan.moviecatalog.core.data.Resource
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

        val moviesAdapter = MovieAdapter()
        moviesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, selected.id)
            startActivity(intent)
        }

        progress_bar_discover_movies.visibility = View.VISIBLE

        viewModel.discoverMovies.observe(viewLifecycleOwner, { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Loading -> progress_bar_discover_movies.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_discover_movies.visibility = View.GONE
                        moviesAdapter.setData(resource.data)
                    }
                    is Resource.Error -> {
                        progress_bar_discover_movies.visibility = View.GONE
                        tv_error_discover_movie.visibility = View.VISIBLE
                        tv_error_discover_movie.text = resource.message
                    }
                }
            }
        })

        moviesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                if (itemCount > 0) {
                    progress_bar_discover_movies.visibility = View.GONE
                    moviesAdapter.unregisterAdapterDataObserver(this)
                }
            }
        })

        with(rv_discover_movies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

    }
}