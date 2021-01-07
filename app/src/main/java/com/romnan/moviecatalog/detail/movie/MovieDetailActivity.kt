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
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.dialog_error.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModel()

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        // Get movie
        val movieId = intent.extras?.getInt(EXTRA_MOVIE_ID, 0)
        if (movieId != null && movieId != 0) {
            viewModel.getMovieDetail(movieId)
        } else showErrorDialog()

        viewModel.movie.observe(this, { populateMovieDetails(it) })
        viewModel.isLoading.observe(this, { showProgressBar(it) })
        viewModel.errorMessage.observe(this, { showErrorDialog(it) })
    }

    private fun showProgressBar(isLoading: Boolean) {
        pb_movie_detail.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorDialog(message: String = getString(R.string.error_message)) {
        pb_movie_detail.visibility = View.GONE
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        dialog.btn_go_back.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateMovieDetails(movie: MovieDetail) {
        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.posterPath))
            .into(iv_movie_detail_poster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.backdropPath))
            .into(iv_movie_detail_backdrop)

        tb_movie_detail_favorite.isChecked = movie.isFavorite
        tb_movie_detail_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setFavoriteMovie(movie, isChecked)
        }

        tv_movie_detail_title.text = movie.title

        tv_movie_detail_score.text = movie.voteAverage.toString()
        pb_movie_detail_score.progress = (movie.voteAverage * 10).roundToInt()

        tv_movie_detail_release_date.text = movie.releaseDate
        tv_movie_detail_duration.text = String.format(getString(R.string.runtime_format), movie.runtime)

        tv_movie_detail_tagline.text = movie.tagline
        if (movie.tagline.isEmpty()) tv_movie_detail_tagline.visibility = View.GONE

        tv_movie_detail_overview.text = movie.overview
        tv_movie_detail_movie_status.text = movie.status
        tv_movie_detail_budget.text = DecimalFormat("#,###").format(movie.budget)
        tv_movie_detail_revenue.text = DecimalFormat("#,###").format(movie.revenue)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}