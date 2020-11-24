package com.romnan.moviecatalog.ui.catalog.movies

import android.content.Intent
import android.view.View
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import com.romnan.showcatalog.ui.shows.PopShowAdapter

class PopMoviesAdapter : PopShowAdapter() {

    override fun openDetail(id: String?, view: View) {
        val intent = Intent(view.context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, id)
        }
        view.context.startActivity(intent)
    }

}