package com.romnan.favorite.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.favorite.databinding.FragmentFavoriteTvSeriesBinding
import com.romnan.moviecatalog.core.adapter.TvSeriesAdapter
import com.romnan.moviecatalog.detail.tvseries.TvSeriesDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvSeriesFragment : Fragment() {

    private var _binding: FragmentFavoriteTvSeriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteTvSeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvSeriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Adapter
        val tvSeriesAdapter = TvSeriesAdapter()
        tvSeriesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), TvSeriesDetailActivity::class.java)
            intent.putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, selected.id)
            startActivity(intent)
        }

        // Setup RecyclerView
        with(binding.rvFavoriteTvSeries) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }

        // Get favorite TvSeries
        viewModel.getFavoriteTvSeries()
        viewModel.favoriteTvSeries.observe(viewLifecycleOwner, { tvSeries ->
            if (tvSeries != null) {
                tvSeriesAdapter.setData(tvSeries)
                binding.laLoadingFavoriteMovie.visibility = View.GONE
                if (tvSeries.isEmpty()) binding.vEmptyFavoriteTvSeries.root.visibility =
                    View.VISIBLE
            }
        })
    }
}