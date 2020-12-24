package com.romnan.moviecatalog.ui.detail.tvseries

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
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
        const val EXTRA_TV_SERIES = "extra_tv_series"
        const val EXTRA_TV_SERIES_ID = "extra_tvShow_id"
    }

    private lateinit var viewModel: TvSeriesDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_series_detail)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        // Setup ViewModel
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[TvSeriesDetailViewModel::class.java]

        // Get intent extras
        val extras = intent.extras
        if (extras != null) {
            val tvSeries = extras.getParcelable<TvSeriesDetail>(EXTRA_TV_SERIES)
            val tvSeriesId = extras.getInt(EXTRA_TV_SERIES_ID)

            if (tvSeries != null) {
                populateTvSeriesDetails(tvSeries)
            } else if (tvSeriesId != 0) {
                viewModel.getTvSeriesDetail(tvSeriesId)
                    .observe(this, { populateTvSeriesDetails(it) })
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
        viewModel.isFavoriteTvSeries(tvSeries.id).observe(this, { isFavorite ->
            btn_favorite.isChecked = isFavorite
        })

        btn_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) viewModel.insertFavoriteTvSeries(tvSeries)
            else viewModel.insertFavoriteTvSeries(tvSeries)
        }

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
        text_first_air_date.text = tvSeries.firstAirDate
        text_season_count.text =
            String.format(getString(R.string.seasons), tvSeries.numberOfSeasons)
        text_tagline.text = tvSeries.tagline
        text_overview.text = tvSeries.overview
        text_tv_series_status.text = tvSeries.status
        text_type.text = tvSeries.type
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}