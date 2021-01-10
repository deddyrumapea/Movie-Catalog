package com.romnan.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.favorite.databinding.FragmentFavoriteMovieBinding
import com.romnan.moviecatalog.core.adapter.MovieAdapter
import com.romnan.moviecatalog.detail.movie.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater)
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
        with(binding.rvFavoriteMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        // Get Favorite Movies
        viewModel.getFavoriteMovies()
        viewModel.favoriteMovies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                moviesAdapter.setData(movies)
                binding.laLoadingFavoriteMovie.visibility = View.GONE
                if (movies.isEmpty()) binding.vEmptyFavoriteMovie.root.visibility = View.VISIBLE
            }
        })
    }
}