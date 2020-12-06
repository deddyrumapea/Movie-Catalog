package com.romnan.moviecatalog.ui.discover.movies

import android.content.Intent
import android.view.View
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import com.romnan.showcatalog.ui.shows.DiscoverAdapter

class DiscoverMovieAdapter : DiscoverAdapter() {

    override fun openDetail(id: String?, view: View) {
        val intent = Intent(view.context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, id)
        }
        view.context.startActivity(intent)
    }

}