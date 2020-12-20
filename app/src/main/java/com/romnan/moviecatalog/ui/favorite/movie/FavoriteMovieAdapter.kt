package com.romnan.moviecatalog.ui.favorite.movie

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
import com.romnan.moviecatalog.data.model.movie.MovieDetail
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class FavoriteMovieAdapter(private val activity: Activity) :
    PagedListAdapter<MovieDetail, FavoriteMovieAdapter.FavMovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieDetail> =
            object : DiffUtil.ItemCallback<MovieDetail>() {
                override fun areItemsTheSame(oldItem: MovieDetail, newItem: MovieDetail): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieDetail,
                    newItem: MovieDetail
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)
        return FavMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = getItem(position) as MovieDetail
        holder.bind(movie)
        holder.itemView.setOnClickListener { openDetail(movie) }
    }

    private fun openDetail(movie: MovieDetail) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
        }
        activity.startActivity(intent)
    }

    inner class FavMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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