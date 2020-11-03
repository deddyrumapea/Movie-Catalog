package com.romnan.moviecatalog.ui.movies

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.Movie
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var moviesList = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>) {
        if (movies == null) return
        moviesList.clear()
        moviesList.addAll(movies)
        Log.d(Companion.TAG, "setMovies: ${moviesList.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = moviesList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                text_movie_item_title.text = movie.title
                text_movie_item_genre.text = movie.genre
                text_movie_item_overview.text = movie.overview

                setOnClickListener {
                    val intent = Intent(context, MovieDetailActivity::class.java).apply {
                        putExtra(MovieDetailActivity.EXTRA_MOVIE, movie.id  )
                    }
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(movie.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_movie_item_poster)
            }
        }

    }

    companion object {
        private const val TAG = "MovieAdapter"
    }
}