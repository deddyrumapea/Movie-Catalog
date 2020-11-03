package com.romnan.moviecatalog.ui.tvshows

import androidx.lifecycle.ViewModel
import com.romnan.moviecatalog.model.TvShow
import com.romnan.moviecatalog.util.DataDummy

class TvShowsViewModel : ViewModel() {
    fun retrieveTvShowData(): ArrayList<TvShow> = DataDummy.generateDummyTvShows()
}