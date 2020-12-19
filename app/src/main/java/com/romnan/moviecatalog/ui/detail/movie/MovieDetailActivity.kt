package com.romnan.moviecatalog.ui.detail.movie

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }

    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Action bar adjustments
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))

        // Setup ViewModel
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        // Get intent extras
        val extras = intent.extras
        if (extras != null) {
            val movie = extras.getParcelable<MovieDetail>(EXTRA_MOVIE)
            val movieId = extras.getInt(EXTRA_MOVIE_ID, 0)

            if (movie != null) {
                populateMovieDetails(movie)
            } else if (movieId != 0) {
                viewModel.getMovieDetail(movieId).observe(this, { populateMovieDetails(it) })
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

    private fun populateMovieDetails(movie: MovieDetail) {
        viewModel.isFavoriteMovie(movie.id).observe(this, { isFavorite ->
            btn_favorite.isChecked = isFavorite
        })

        btn_favorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) viewModel.insertFavoriteMovie(movie)
            else viewModel.deleteFavoriteMovie(movie)
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
}