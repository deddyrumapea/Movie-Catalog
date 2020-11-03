package com.romnan.moviecatalog.ui.detail.tvshows

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.TvShow
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        // Change action bar color
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimaryDark
                )
            )
        )

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SHOW)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                populateTvShowDetails(viewModel.getTvShow())
            }
        }
    }

    private fun populateTvShowDetails(tvShow: TvShow) {
        Glide.with(this)
            .load(tvShow.poster)
            .into(image_tvshow_background)

        Glide.with(this)
            .load(tvShow.poster)
            .into(image_tvshow_poster)

        text_tvshow_title.text = tvShow.title
        text_score.text = tvShow.score.toString()
        progress_bar_score.progress = tvShow.score
        button_play_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(tvShow.trailerUrl))
            startActivity(intent)
        }
        text_duration.text = tvShow.duration
        text_genre.text = tvShow.genre
        if (tvShow.tagline != ""){
            text_tagline.text = tvShow.tagline
        } else {
            text_tagline.visibility = GONE
        }
        text_overview.text = tvShow.overview
        text_creator.text = tvShow.creator
        text_casts.text = tvShow.casts
        text_status.text = tvShow.status
        text_language.text = tvShow.language
        text_type.text = tvShow.type
        text_last_season.text = tvShow.lastSeason
        text_keywords.text = tvShow.keyword
    }
}