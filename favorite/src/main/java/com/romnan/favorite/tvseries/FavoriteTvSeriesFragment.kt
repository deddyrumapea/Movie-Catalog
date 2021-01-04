package com.romnan.favorite.tvseries

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.favorite.R
import com.romnan.moviecatalog.core.adapter.TvSeriesAdapter
import com.romnan.moviecatalog.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_tv_series.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvSeriesFragment : Fragment() {

    private val viewModel: FavoriteTvSeriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_favorite_tv_series, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity == null) return
        val tvSeriesAdapter = TvSeriesAdapter()
        tvSeriesAdapter.onItemClick = { selected ->
            val intent = Intent(requireActivity(), TvSeriesDetailActivity::class.java)
            intent.putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, selected.id)
            startActivity(intent)
        }
        progress_bar_favorite_tv_series.visibility = View.VISIBLE

        viewModel.favoriteTvSeries.observe(viewLifecycleOwner, { tvSeries ->
            if (tvSeries != null) {
                tvSeriesAdapter.setData(tvSeries)
                progress_bar_favorite_tv_series.visibility = View.GONE
            }
        })

        with(rv_favorite_tv_series) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }
    }
}