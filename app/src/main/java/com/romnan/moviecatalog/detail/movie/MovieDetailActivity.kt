package com.romnan.moviecatalog.detail.movie

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.core.data.Resource
import com.romnan.moviecatalog.core.domain.model.movie.Movie
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
            viewModel.getMovieDetail(movieId).observe(this, { resource ->
                if (resource != null) {
                    when (resource) {
                        is Resource.Loading -> progress_bar_movie_detail.visibility = View.VISIBLE
                        is Resource.Success -> populateMovieDetails(resource.data)
                        is Resource.Error -> showErrorDialog()
                    }
                }
            })
        } else showErrorDialog()
    }

    private fun populateMovieDetails(movie: Movie?) {
        if (movie == null) return

        progress_bar_movie_detail.visibility = View.GONE

        btn_favorite.isChecked = movie.isFavorite

        btn_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.setFavoriteMovie(movie, isChecked)
        }

        progress_bar_movie_detail.visibility = View.GONE

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.posterPath))
            .into(image_movie_poster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.backdropPath))
            .into(image_movie_backdrop)

        text_movie_title.text = movie.title
        text_score.text = movie.voteAverage.toString()
        progress_bar_score.progress = (movie.voteAverage * 10).roundToInt()
        text_release_date.text = movie.releaseDate
        text_duration.text = String.format(getString(R.string.runtime_format), movie.runtime)
        text_tagline.text = movie.tagline
        if (movie.tagline.isEmpty()) text_tagline.visibility = View.GONE
        text_overview.text = movie.overview
        text_movie_status.text = movie.status
        text_budget.text = DecimalFormat("#,###").format(movie.budget)
        text_revenue.text = DecimalFormat("#,###").format(movie.revenue)
    }

    private fun showErrorDialog() {
        progress_bar_movie_detail.visibility = View.GONE
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        dialog.button_go_back.setOnClickListener { finish() }
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}