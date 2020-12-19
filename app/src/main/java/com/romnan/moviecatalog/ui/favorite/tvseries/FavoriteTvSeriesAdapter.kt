
package com.romnan.moviecatalog.ui.favorite.tvseries

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.data.model.TvSeriesDetail
import com.romnan.moviecatalog.ui.detail.tvseries.TvSeriesDetailActivity
import kotlinx.android.synthetic.main.item_popular_show.view.*

class FavoriteTvSeriesAdapter :
    RecyclerView.Adapter<FavoriteTvSeriesAdapter.FavoriteTvSeriesViewHolder>() {

    private var tvSeriesList = ArrayList<TvSeriesDetail>()

    fun setTvSeries(tvSeries: List<TvSeriesDetail>) {
        tvSeriesList.clear()
        tvSeriesList.addAll(tvSeries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvSeriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popular_show, parent, false)

        return FavoriteTvSeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteTvSeriesViewHolder, position: Int) {
        val tvSeries = tvSeriesList[position]
        holder.bind(tvSeries)
        holder.itemView.setOnClickListener { openDetail(tvSeries, it) }
    }

    override fun getItemCount() = tvSeriesList.size


    private fun openDetail(tvSeries: TvSeriesDetail, view: View) {
        val intent = Intent(view.context, TvSeriesDetailActivity::class.java).apply {
            putExtra(TvSeriesDetailActivity.EXTRA_TV_SERIES, tvSeries)
        }
        view.context.startActivity(intent)
    }

    class FavoriteTvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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