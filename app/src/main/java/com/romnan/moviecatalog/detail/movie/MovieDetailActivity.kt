package com.romnan.moviecatalog.detail.movie

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.presentation.movie.MovieDetail
import com.romnan.moviecatalog.databinding.ActivityMovieDetailBinding
import com.romnan.moviecatalog.databinding.DialogErrorBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModel()

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        getMovieDetail()

        viewModel.movie.observe(this, { populateMovieDetails(it) })
        viewModel.isLoading.observe(this, { showProgressBar(it) })
        viewModel.errorMessage.observe(this, { showErrorDialog(it) })
    }

    private fun getMovieDetail() {
        val movieId = intent.extras?.getInt(EXTRA_MOVIE_ID, 0)
        if (movieId != null && movieId != 0) {
            viewModel.getMovieDetail(movieId)
        } else showErrorDialog()
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.pbMovieDetail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String = getString(R.string.error_message)) {
        val dialog = Dialog(this)
        val dialogErrorBinding = DialogErrorBinding.inflate(layoutInflater)
        dialog.setContentView(dialogErrorBinding.root)
        dialogErrorBinding.btnRetry.setOnClickListener {
            getMovieDetail()
            dialog.dismiss()
        }
        dialogErrorBinding.tvErrorMessage.text = message
        dialog.show()
    }

    private fun populateMovieDetails(movie: MovieDetail) {
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.posterPath))
            .into(binding.ivMovieDetailPoster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.backdropPath))
            .into(binding.ivMovieDetailBackdrop)

        binding.tbMovieDetailFavorite.isChecked = movie.isFavorite
        binding.tbMovieDetailFavorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFavoriteMovie(movie, isChecked)
        }

        binding.tvMovieDetailTitle.text = movie.title

        binding.tvMovieDetailScore.text = movie.voteAverage.toString()
        binding.pbMovieDetailScore.progress = (movie.voteAverage * 10).roundToInt()

        binding.tvMovieDetailReleaseDate.text = movie.releaseDate
        binding.tvMovieDetailDuration.text =
            String.format(getString(R.string.runtime_format), movie.runtime)

        binding.tvMovieDetailTagline.text = movie.tagline
        if (movie.tagline.isEmpty()) binding.tvMovieDetailTagline.visibility = View.GONE

        binding.tvMovieDetailOverview.text = movie.overview
        binding.tvMovieDetailMovieStatus.text = movie.status
        binding.tvMovieDetailBudget.text = DecimalFormat("#,###").format(movie.budget)
        binding.tvMovieDetailRevenue.text = DecimalFormat("#,###").format(movie.revenue)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}