package com.romnan.moviecatalog.detail.tvseries

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries
import kotlinx.android.synthetic.main.activity_tv_series_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class TvSeriesDetailActivity : AppCompatActivity() {

    private val viewModel: TvSeriesDetailViewModel by viewModel()

    companion object {
        const val EXTRA_TV_SERIES_ID = "extra_tvShow_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_series_detail)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        // Get tv series
        val tvSeriesId = intent.extras?.getInt(EXTRA_TV_SERIES_ID, 0)
        if (tvSeriesId != null && tvSeriesId != 0) {
            viewModel.getTvSeriesDetail(tvSeriesId).observe(this, { resource ->
                if (resource != null) {
                    when (resource) {
                        is Resource.Loading -> progress_bar_tv_series_detail.visibility =
                            View.VISIBLE
                        is Resource.Success -> populateTvSeriesDetails(resource.data)
                        is Resource.Error -> showErrorDialog()
                    }
                }
            })
        } else showErrorDialog()
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        val btnGoBack = dialog.findViewById<Button>(R.id.button_go_back)
        btnGoBack.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvSeriesDetails(tvSeries: TvSeries?) {
        if (tvSeries == null) return

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
        text_overview.text = tvSeries.overview
        text_tv_series_status.text = tvSeries.status
        text_type.text = tvSeries.type
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}