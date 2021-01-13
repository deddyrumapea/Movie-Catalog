package com.romnan.moviecatalog.discover.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.core.adapter.MovieAdapter
import com.romnan.moviecatalog.databinding.FragmentDiscoverMovieBinding
import com.romnan.moviecatalog.detail.movie.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverMovieFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverMovieBinding

    private val viewModel: DiscoverMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        with(binding.rvDiscoverMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        viewModel.getDiscoverMovies()
        viewModel.discoverMovies.observe(viewLifecycleOwner, { moviesAdapter.setData(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, { showProgressBar(it) })
        viewModel.errorMessage.observe(viewLifecycleOwner, { showErrorView(it) })
    }

    private fun showErrorView(message: String) {
        val errorView = binding.vErrorDiscoverMovie
        errorView.root.visibility = View.VISIBLE
        errorView.tvErrorMessage.text = message
        errorView.btnRetry.setOnClickListener {
            errorView.root.visibility = View.GONE
            viewModel.getDiscoverMovies()
        }
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.laLoadingDiscoverMovie.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}