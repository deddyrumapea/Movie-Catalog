package com.romnan.moviecatalog.discover.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.adapter.TvSeriesAdapter
import com.romnan.moviecatalog.core.data.Resource
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
        val tvSeriesAdapter = TvSeriesAdapter()
        tvSeriesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), TvSeriesDetailActivity::class.java)
            intent.putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, selected.id)
            startActivity(intent)
        }

        viewModel.discoverTvSeries.observe(viewLifecycleOwner, { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Loading -> progress_bar_discover_tv_series.visibility =
                        View.VISIBLE
                    is Resource.Success -> {
                        progress_bar_discover_tv_series.visibility = View.GONE
                        tvSeriesAdapter.setData(resource.data)
                    }
                    is Resource.Error -> {
                        progress_bar_discover_tv_series.visibility = View.GONE
                        tv_error_discover_tv_series.visibility = View.VISIBLE
                        tv_error_discover_tv_series.text = resource.message
                    }
                }
            }
        })

        tvSeriesAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                if (itemCount > 0) {
                    progress_bar_discover_tv_series.visibility = View.GONE
                    tvSeriesAdapter.unregisterAdapterDataObserver(this)
                }
            }
        })

        with(rv_discover_tv_series) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }
    }
}