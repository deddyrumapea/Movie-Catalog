package com.romnan.moviecatalog.ui.detail.tvshows

import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.model.TvShow
import com.romnan.moviecatalog.util.DataDummy

class TvShowDetailViewModel : ViewModel() {

    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): TvShow {
        lateinit var tvShow: TvShow
        val tvShows = DataDummy.generateDummyTvShows()

        // Search corresponding TvShow
        for (tvShowItem in tvShows) {
            if (tvShowItem.id == tvShowId) {
                tvShow = tvShowItem
            }
        }
        return tvShow
    }
}