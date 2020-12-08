package com.romnan.moviecatalog.ui.detail.tvseries

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tv_series_detail.*
import kotlin.math.roundToInt

class TvSeriesDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SERIES_ID = "extra_tvShow_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_series_detail)

        // Change action bar color and title, set up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
        supportActionBar?.title = getString(R.string.tv_series_detail)

        // Setup ViewModel
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[TvSeriesDetailViewModel::class.java]

        // Get intent extras
        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SERIES_ID)
            if (tvShowId != null) {
                viewModel.getTvSeriesDetail(tvShowId).observe(this, { populateTvSeriesDetails(it) })
            } else showErrorDialog()
        } else showErrorDialog()
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        val btnGoBack = dialog.findViewById<Button>(R.id.button_go_back)
        btnGoBack.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvSeriesDetails(tvSeries: TvSeriesDetail) {
        progress_bar_tv_series_detail.visibility = View.GONE

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.posterPath))
            .into(image_tv_series_poster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.backdropPath))
            .into(image_tv_series_backdrop)

        text_tv_series_title.text = tvSeries.name
        text_score.text = tvSeries.voteAverage.toString()
        progress_bar_score.progress = (tvSeries.voteAverage * 10).roundToInt()
        button_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(
                Uri.parse(
                    String.format(
                        getString(R.string.youtube_base_url),
                        tvSeries.videos.results[0].key
                    )
                )
            )
            startActivity(intent)
        }
        if (tvSeries.episodeRunTime.isNotEmpty()) text_duration.text =
            String.format(getString(R.string.runtime_format), tvSeries.episodeRunTime[0])

        var genre = ""
        tvSeries.genres.forEach {
            genre += " ${it.name} "
        }
        text_genre.text = genre

        text_tagline.text = tvSeries.tagline
        text_overview.text = tvSeries.overview
        if (tvSeries.createdBy.isNotEmpty()) text_creator.text = tvSeries.createdBy[0].name
        if (tvSeries.networks.isNotEmpty()) text_networks.text = tvSeries.networks[0].name
        text_tv_series_status.text = tvSeries.status
        text_type.text = tvSeries.type
        if (tvSeries.spokenLanguages.isNotEmpty()) text_language.text =
            tvSeries.spokenLanguages[0].name
        if (tvSeries.productionCountries.isNotEmpty()) text_production_countries.text =
            tvSeries.productionCountries[0].name
        text_last_aired_episode.text = String.format(
            getString(R.string.last_aired_format),
            tvSeries.lastEpisodeToAir.seasonNumber,
            tvSeries.lastEpisodeToAir.episodeNumber,
            tvSeries.lastEpisodeToAir.overview,
            tvSeries.lastEpisodeToAir.airDate
        )
    }

}