package com.romnan.moviecatalog.ui.catalog.tvshows

import android.content.Intent
import android.view.View
import com.romnan.moviecatalog.ui.detail.tvshows.TvShowDetailActivity
import com.romnan.showcatalog.ui.shows.PopShowAdapter

class PopTvShowsAdapter : PopShowAdapter() {
    override fun openDetail(id: String?, view: View) {
        val intent = Intent(view.context, TvShowDetailActivity::class.java).apply {
            putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, id)
        }
        view.context.startActivity(intent)
    }
}