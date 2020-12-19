package com.romnan.moviecatalog.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    private var moviesList = ArrayList<MovieDetail>()

    fun setMovies(movies: List<MovieDetail>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)

        return FavoriteMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { openDetail(movie, it) }
    }

    override fun getItemCount() = moviesList.size


    private fun openDetail(movie: MovieDetail, view: View) {
        val intent = Intent(view.context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        }
        view.context.startActivity(intent)
    }

    class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieDetail) {
            with(itemView) {
                text_pop_show_item_title.text = movie.title
                text_pop_show_item_release_date.text = movie.releaseDate
                text_pop_show_item_overview.text = movie.overview

                Glide.with(context)
                    .load(
                        String.format(
                            context.getString(R.string.image_base_url),
                            movie.posterPath
                        )
                    ).apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_pop_show_item_poster)
            }
        }
    }
}