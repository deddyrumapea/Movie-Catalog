package com.romnan.moviecatalog.ui.detail.tvshows

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.TvShowDetail
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import kotlin.math.roundToInt

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "extra_tvShow_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        // Change action bar color and title, set up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
        supportActionBar?.title = getString(R.string.tv_show_detail)

        // Setup ViewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TvShowDetailViewModel::class.java]

        // Get intent extras
        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SHOW_ID)
            if (tvShowId != null) {
                viewModel.getTvShowDetail(tvShowId)
            } else showErrorDialog()
        } else showErrorDialog()

        viewModel.tvShowDetail.observe(this, { populateTvShowDetails(it) })
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        val btnGoBack = dialog.findViewById<Button>(R.id.button_go_back)
        btnGoBack.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvShowDetails(tvShow: TvShowDetail) {
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvShow.posterPath))
            .into(image_tvshow_poster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvShow.backdropPath))
            .into(image_tvshow_backdrop)

        text_tvshow_title.text = tvShow.name
        text_score.text = tvShow.voteAverage.toString()
        progress_bar_score.progress = (tvShow.voteAverage * 10).roundToInt()
        button_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(
                Uri.parse(
                    String.format(
                        getString(R.string.youtube_base_url),
                        tvShow.videos.results[0].key
                    )
                )
            )
            startActivity(intent)
        }

        text_duration.text = String.format(getString(R.string.runtime_format), tvShow.episodeRunTime[0])

        var genre = ""
        tvShow.genres.forEach {
            genre += " ${it.name} "
        }
        text_genre.text = genre

        text_tagline.text = tvShow.tagline
        text_overview.text = tvShow.overview
        text_creator.text = tvShow.createdBy[0].name
        text_networks.text = tvShow.networks[0].name
        text_status.text = tvShow.status
        text_type.text = tvShow.type
        text_language.text = tvShow.spokenLanguages[0].name
        text_production_countries.text = tvShow.productionCountries[0].name
        text_last_aired_episode.text = String.format(
            getString(R.string.last_aired_format),
            tvShow.lastEpisodeToAir.seasonNumber,
            tvShow.lastEpisodeToAir.episodeNumber,
            tvShow.lastEpisodeToAir.overview,
            tvShow.lastEpisodeToAir.airDate
        )
    }

}