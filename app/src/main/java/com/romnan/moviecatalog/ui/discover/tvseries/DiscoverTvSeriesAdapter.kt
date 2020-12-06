package com.romnan.moviecatalog.ui.discover.tvseries

import android.content.Intent
import android.view.View
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailActivity
import com.romnan.showcatalog.ui.shows.DiscoverAdapter

class DiscoverTvSeriesAdapter : DiscoverAdapter() {
    override fun openDetail(id: String?, view: View) {
        val intent = Intent(view.context, TvSeriesDetailActivity::class.java).apply {
            putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, id)
        }
        view.context.startActivity(intent)
    }
}