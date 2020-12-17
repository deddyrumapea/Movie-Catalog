package com.romnan.moviecatalog.ui.discover.tvseries

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.tvseries.PopularTvSeries
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class DiscoverTvSeriesAdapter :
    RecyclerView.Adapter<DiscoverTvSeriesAdapter.PopularTvSeriesViewHolder>() {

    private var tvSeriessList = ArrayList<PopularTvSeries>()

    fun setTvSeries(tvSeriess: List<PopularTvSeries>) {
        tvSeriessList.clear()
        tvSeriessList.addAll(tvSeriess)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvSeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)
        return PopularTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holderPopularTvSeries: PopularTvSeriesViewHolder, position: Int) {
        val tvSeries = tvSeriessList[position]
        holderPopularTvSeries.bind(tvSeries)
        holderPopularTvSeries.itemView.setOnClickListener { openDetail(tvSeries.id, it) }
    }

    override fun getItemCount() = tvSeriessList.size


    fun openDetail(id: Int, view: View) {
        val intent = Intent(view.context, TvSeriesDetailActivity::class.java).apply {
            putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES_ID, id)
        }
        view.context.startActivity(intent)
    }

    class PopularTvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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