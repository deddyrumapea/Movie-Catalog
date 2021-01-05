package com.romnan.moviecatalog.discover.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.adapter.TvSeriesAdapter
import com.romnan.moviecatalog.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.fragment_discover_tv_series.*
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverTvSeriesFragment : Fragment() {

    private val viewModel: DiscoverTvSeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_discover_tv_series, container, false)

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
        with(rv_discover_tv_series) {
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
        progress_bar_discover_tv_series.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String) {
        tv_error_discover_tv_series.visibility = View.VISIBLE
        tv_error_discover_tv_series.text = message
    }
}