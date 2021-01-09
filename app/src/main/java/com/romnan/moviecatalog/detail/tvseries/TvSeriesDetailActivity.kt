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
import com.romnan.moviecatalog.databinding.ActivityTvSeriesDetailBinding
import com.romnan.moviecatalog.databinding.DialogErrorBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class TvSeriesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvSeriesDetailBinding

    private val viewModel: TvSeriesDetailViewModel by viewModel()

    companion object {
        const val EXTRA_TV_SERIES_ID = "extra_tv_series_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvSeriesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.pbTvSeriesDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        val dialogErrorBinding = DialogErrorBinding.inflate(layoutInflater)
        dialog.setContentView(dialogErrorBinding.root)
        dialogErrorBinding.btnGoBack.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateTvSeriesDetails(tvSeries: TvSeriesDetail) {
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.posterPath))
            .into(binding.ivTvSeriesDetailPoster)
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), tvSeries.backdropPath))
            .into(binding.ivTvSeriesDetailBackdrop)

        binding.tbTvSeriesDetailFavorite.isChecked = tvSeries.isFavorite
        binding.tbTvSeriesDetailFavorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFavoriteTvSeries(tvSeries, isChecked)
        }

        binding.tvTvSeriesDetailName.text = tvSeries.name

        binding.tvTvSeriesDetailScore.text = tvSeries.voteAverage.toString()
        binding.pbTvSeriesDetailScore.progress = (tvSeries.voteAverage * 10).roundToInt()

        binding.tvTvSeriesDetailFirstAirDate.text = tvSeries.firstAirDate

        binding.tvTvSeriesDetailSeasonCount.text = resources.getQuantityString(
            R.plurals.seasons,
            tvSeries.numberOfSeasons,
            tvSeries.numberOfSeasons
        )

        binding.tvTvSeriesDetailTagline.text = tvSeries.tagline
        if (tvSeries.tagline.isEmpty()) binding.tvTvSeriesDetailTagline.visibility = View.GONE

        binding.tvTvSeriesDetailOverview.text = tvSeries.overview
        binding.tvTvSeriesDetailStatus.text = tvSeries.status
        binding.tvTvSeriesDetailType.text = tvSeries.type
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}