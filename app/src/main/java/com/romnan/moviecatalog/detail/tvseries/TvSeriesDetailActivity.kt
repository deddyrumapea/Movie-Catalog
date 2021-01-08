package com.romnan.moviecatalog.detail.tvseries

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.presentation.tvseries.TvSeriesDetail
import kotlinx.android.synthetic.main.activity_movie_detail.*
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
        viewModel.errorMessage.observe(this, { showErrorDialog() })
    }

    private fun showProgressBar(isLoading: Boolean) {
        pb_tv_series_detail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        dialog.btn_go_back.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvSeriesDetails(tvSeries: TvSeriesDetail) {
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.posterPath))
            .into(iv_tv_series_detail_poster)
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.backdropPath))
            .into(iv_tv_series_detail_backdrop)

        tb_tv_series_detail_favorite.isChecked = tvSeries.isFavorite
        tb_tv_series_detail_favorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFavoriteTvSeries(tvSeries, isChecked)
        }

        tv_tv_series_detail_name.text = tvSeries.name

        tv_tv_series_detail_score.text = tvSeries.voteAverage.toString()
        pb_tv_series_detail_score.progress = (tvSeries.voteAverage * 10).roundToInt()

        tv_tv_series_detail_first_air_date.text = tvSeries.firstAirDate

        tv_tv_series_detail_season_count.text = resources.getQuantityString(
            R.plurals.seasons,
            tvSeries.numberOfSeasons,
            tvSeries.numberOfSeasons
        )

        tv_tv_series_detail_tagline.text = tvSeries.tagline
        if (tvSeries.tagline.isEmpty()) tv_tv_series_detail_tagline.visibility = View.GONE

        tv_tv_series_detail_overview.text = tvSeries.overview
        tv_tv_series_detail_status.text = tvSeries.status
        tv_tv_series_detail_type.text = tvSeries.type
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}