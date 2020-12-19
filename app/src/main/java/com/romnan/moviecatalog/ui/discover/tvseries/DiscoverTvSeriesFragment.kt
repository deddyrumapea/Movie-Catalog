package com.romnan.moviecatalog.ui.discover.tvseries

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
import kotlinx.android.synthetic.main.fragment_discover_tv_series.*


class DiscoverTvSeriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_discover_tv_series, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel =
                ViewModelProvider(requireActivity(), factory)[DiscoverTvSeriesViewModel::class.java]

            val tvSeriesAdapter = DiscoverTvSeriesAdapter()

            viewModel.getPopularTvSeries()

            viewModel.getPopularTvSeries().observe(viewLifecycleOwner, { tvSeries ->
                tvSeriesAdapter.setTvSeries(tvSeries)
                progress_bar_popular_tv_series.visibility = View.GONE
                Log.d(TAG, "onActivityCreated: ${tvSeries.size}")
            })

            with(rv_popular_tv_series) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvSeriesAdapter
            }
        }
    }

    companion object {
        private const val TAG = "DiscoverTvSeriesFrag"
    }
}