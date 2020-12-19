package com.romnan.moviecatalog.ui.discover.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class DiscoverMovieAdapter : RecyclerView.Adapter<DiscoverMovieAdapter.PopularMovieViewHolder>() {

    private var moviesList = ArrayList<PopularMovie>()

    fun setMovies(movies: List<PopularMovie>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)
        return PopularMovieViewHolder(view)
    }

    override fun onBindViewHolder(holderPopularMovie: PopularMovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holderPopularMovie.bind(movie)
        holderPopularMovie.itemView.setOnClickListener { openDetail(movie.id, it) }
    }

    override fun getItemCount() = moviesList.size

    fun openDetail(id: Int, view: View) {
        val intent = Intent(view.context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, id)
        }
        view.context.startActivity(intent)
    }

    class PopularMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: PopularMovie) {
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
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_pop_show_item_poster)
            }
        }
    }
}