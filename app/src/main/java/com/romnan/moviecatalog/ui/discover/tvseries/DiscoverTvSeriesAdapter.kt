package com.romnan.moviecatalog.ui.discover.tvseries

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
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class DiscoverTvSeriesAdapter(private val activity: Activity) :
    PagedListAdapter<PopularTvSeries, DiscoverTvSeriesAdapter.TvSeriesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<PopularTvSeries> =
            object : DiffUtil.ItemCallback<PopularTvSeries>() {
                override fun areItemsTheSame(
                    oldItem: PopularTvSeries,
                    newItem: PopularTvSeries
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: PopularTvSeries,
                    newItem: PopularTvSeries
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)
        return TvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        val tvSeries = getItem(position) as PopularTvSeries
        holder.bind(tvSeries)
        holder.itemView.setOnClickListener { openDetail(tvSeries.id) }
    }

    private fun openDetail(tvSeriesId: Int) {
        val intent = Intent(activity, TvSeriesDetailActivity::class.java).apply {
            putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, tvSeriesId)
        }
        activity.startActivity(intent)
    }

    inner class TvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvSeries: PopularTvSeries) {
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
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_pop_show_item_poster)
            }
        }

    }
}