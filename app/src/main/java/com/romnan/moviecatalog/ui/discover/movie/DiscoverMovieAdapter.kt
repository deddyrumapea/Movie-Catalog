package com.romnan.moviecatalog.ui.discover.movie

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.movie.PopularMovie
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*


class DiscoverMovieAdapter(private val activity: Activity) :
    PagedListAdapter<PopularMovie, DiscoverMovieAdapter.PopularMovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<PopularMovie> =
            object : DiffUtil.ItemCallback<PopularMovie>() {
                override fun areItemsTheSame(
                    oldItem: PopularMovie,
                    newItem: PopularMovie
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: PopularMovie,
                    newItem: PopularMovie
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)
        return PopularMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val movie = getItem(position) as PopularMovie
        holder.bind(movie)
        holder.itemView.setOnClickListener { openDetail(movie.id) }
    }

    fun openDetail(id: Int) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, id)
        }
        activity.startActivity(intent)
    }

    inner class PopularMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
