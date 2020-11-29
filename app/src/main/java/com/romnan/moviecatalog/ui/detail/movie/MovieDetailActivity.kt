package com.romnan.moviecatalog.ui.detail.movie

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
import com.romnan.moviecatalog.model.MovieDetail
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.button_trailer
import kotlinx.android.synthetic.main.activity_movie_detail.progress_bar_score
import kotlinx.android.synthetic.main.activity_movie_detail.text_duration
import kotlinx.android.synthetic.main.activity_movie_detail.text_language
import kotlinx.android.synthetic.main.activity_movie_detail.text_overview
import kotlinx.android.synthetic.main.activity_movie_detail.text_score
import kotlinx.android.synthetic.main.activity_movie_detail.text_status
import kotlinx.android.synthetic.main.activity_movie_detail.text_tagline
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        private const val TAG = "MovieDetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Change action bar color and title, set up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
        supportActionBar?.title = getString(R.string.movie_detail)

        // Setup ViewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieDetailViewModel::class.java]

        // Get intent extras
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            if (movieId != null) {
                viewModel.getMovieDetail(movieId)
            } else showErrorDialog()
        } else showErrorDialog()

        viewModel.movieDetail.observe(this, { populateMovieDetails(it) })
    }

    private fun showErrorDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_error)
        val btnGoBack = dialog.findViewById<Button>(R.id.button_go_back)
        btnGoBack.setOnClickListener { finish() }
        dialog.show()
    }

    private fun populateMovieDetails(movie: MovieDetail) {
        progress_bar_movie_detail.visibility = View.GONE;

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.posterPath))
            .into(image_movie_poster)

        Glide.with(this)
            .load(String.format(getString(R.string.image_base_url), movie.backdropPath))
            .into(image_movie_backdrop)

        text_movie_title.text = movie.title
        text_score.text = movie.voteAverage.toString()
        progress_bar_score.progress = (movie.voteAverage * 10).roundToInt()
        button_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(
                Uri.parse(
                    String.format(
                        getString(R.string.youtube_base_url),
                        movie.videos.results[0].key
                    )
                )
            )
            startActivity(intent)
        }

        var genre = ""
        movie.genres.forEach {
            genre += " ${it.name} "
        }
        text_genre_movie.text = genre

        text_release_date.text = movie.releaseDate
        text_duration.text = String.format(getString(R.string.runtime_format), movie.runtime)
        text_tagline.text = movie.tagline
        text_overview.text = movie.overview
        text_status.text = movie.status
        text_language.text = movie.spokenLanguages[0].name
        text_budget.text = DecimalFormat("#,###").format(movie.budget)
        text_revenue.text = DecimalFormat("#,###").format(movie.revenue)
    }
}