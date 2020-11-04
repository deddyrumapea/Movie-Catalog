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
import com.romnan.moviecatalog.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.dialog_error.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Change action bar color and title, set up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
        supportActionBar?.title = getString(R.string.movie_detail)

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

    private fun populateMovieDetails(movie: Movie) {
        Glide.with(this)
            .load(movie.poster)
            .into(image_movie_poster)

        Glide.with(this)
            .load(movie.poster)
            .into(image_movie_background)

        text_movie_title.text = movie.title
        text_score.text = movie.score.toString()
        progress_bar_score.progress = movie.score
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