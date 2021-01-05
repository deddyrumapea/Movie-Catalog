package com.romnan.moviecatalog.detail.tvseries

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.presentation.TvSeriesPresentation
import kotlinx.android.synthetic.main.activity_tv_series_detail.*
import kotlinx.android.synthetic.main.dialog_error.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class TvSeriesDetailActivity : AppCompatActivity() {

    private val viewModel: TvSeriesDetailViewModel by viewModel()

    companion object {
        const val EXTRA_TV_SERIES_ID = "extra_tv_series_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_series_detail)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        // Get tv series
        val tvSeriesId = intent.extras?.getInt(EXTRA_TV_SERIES_ID, 0)
        if (tvSeriesId != null && tvSeriesId != 0) {
            viewModel.getTvSeriesDetail(tvSeriesId)
        } else showErrorDialog()

        viewModel.tvSeries.observe(this, { populateTvSeriesDetails(it) })
        viewModel.isLoading.observe(this, { showProgressBar(it) })
        viewModel.errorMessage.observe(this, { showErrorDialog(it) })
    }

    private fun showProgressBar(isLoading: Boolean) {
        progress_bar_tv_series_detail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String = getString(R.string.error_message)) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        dialog.text_error_message.text = message
        dialog.button_go_back.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvSeriesDetails(tvSeries: TvSeriesPresentation) {
        btn_favorite.isChecked = tvSeries.isFavorite

        btn_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setFavoriteTvSeries(tvSeries, isChecked)
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
            resources.getQuantityString(
                R.plurals.seasons,
                tvSeries.numberOfSeasons,
                tvSeries.numberOfSeasons
            )
        text_tagline.text = tvSeries.tagline
        if (tvSeries.tagline.isEmpty()) text_tagline.visibility = View.GONE
        text_overview.text = tvSeries.overview
        text_tv_series_status.text = tvSeries.status
        text_type.text = tvSeries.type
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}