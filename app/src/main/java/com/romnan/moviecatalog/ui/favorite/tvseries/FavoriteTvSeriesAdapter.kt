package com.romnan.moviecatalog.ui.favorite.tvseries

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
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class FavoriteTvSeriesAdapter(private val activity: Activity) :
    PagedListAdapter<TvSeriesDetail, FavoriteTvSeriesAdapter.FavTvSeriesViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvSeriesDetail> =
            object : DiffUtil.ItemCallback<TvSeriesDetail>() {
                override fun areItemsTheSame(
                    oldItem: TvSeriesDetail,
                    newItem: TvSeriesDetail
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: TvSeriesDetail,
                    newItem: TvSeriesDetail
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvSeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)

        return FavTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTvSeriesViewHolder, position: Int) {
        val tvSeries = getItem(position) as TvSeriesDetail
        holder.bind(tvSeries)
        holder.itemView.setOnClickListener { openDetail(tvSeries) }
    }

    private fun openDetail(tvSeries: TvSeriesDetail) {
        val intent = Intent(activity, TvSeriesDetailActivity::class.java).apply {
            putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES, tvSeries)
        }
        activity.startActivity(intent)
    }

    inner class FavTvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvSeries: TvSeriesDetail) {
            with(itemView) {
                text_pop_show_item_title.text = tvSeries.name
                text_pop_show_item_release_date.text = tvSeries.firstAirDate
                text_pop_show_item_overview.text = tvSeries.overview

                Glide.with(context)
                    .load(
                        String.format(
                            context.getString(R.string.image_base_url),
                            tvSeries.posterPath
                        )
                    ).apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_pop_show_item_poster)
            }
        }
    }
}
