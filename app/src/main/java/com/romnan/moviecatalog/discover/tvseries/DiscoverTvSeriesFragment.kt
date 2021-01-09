package com.romnan.moviecatalog.discover.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.core.adapter.TvSeriesAdapter
import com.romnan.moviecatalog.databinding.FragmentDiscoverTvSeriesBinding
import com.romnan.moviecatalog.detail.tvseries.TvSeriesDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverTvSeriesFragment : Fragment() {

    private var _binding: FragmentDiscoverTvSeriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DiscoverTvSeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverTvSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup adapter
        val tvSeriesAdapter = TvSeriesAdapter()
        tvSeriesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), TvSeriesDetailActivity::class.java)
            intent.putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, selected.id)
            startActivity(intent)
        }

        // Setup RecyclerView
        with(binding.rvDiscoverTvSeries) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }

        viewModel.getDiscoverTvSeries()
        viewModel.discoverTvSeries.observe(viewLifecycleOwner, { tvSeriesAdapter.setData(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, { showProgressBar(it) })
        viewModel.errorMessage.observe(viewLifecycleOwner, { showErrorDialog(it) })
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.pbDiscoverTvSeries.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String) {
        binding.tvErrorDiscoverTvSeries.visibility = View.VISIBLE
        binding.tvErrorDiscoverTvSeries.text = message
    }
}