package com.romnan.moviecatalog.ui.detail.movie

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Change action bar color
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimaryDark
                )
            )
        )

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                populateMovieDetails(viewModel.getMovie())
            }
        }
    }

    private fun populateMovieDetails(movie: Movie) {
        Glide.with(this)
            .load(movie.poster)
            .into(image_movie_poster)

        Glide.with(this)
            .load(movie.poster)
            .into(image_movie_background)

        text_movie_title.text = movie.title
        text_score.text = movie.score.toString()
        progress_bar_score.setProgress(movie.score)
        button_play_trailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(movie.trailerUrl))
            startActivity(intent)
        }

        text_release_date.text = movie.releaseDate
        text_duration.text = movie.duration
        text_genre.text = movie.genre
        if (movie.tagline != "") {
            text_tagline.text = movie.tagline
        } else {
            text_tagline.visibility = View.GONE
        }
        text_overview.text = movie.overview
        text_director.text = movie.director
        text_casts.text = movie.casts
        text_status.text = movie.status
        text_language.text = movie.language
        text_budget.text = movie.budget
        text_revenue.text = movie.revenue
        text_keywords.text = movie.keyword
    }
}