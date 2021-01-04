package com.romnan.moviecatalog.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.core.R
import com.romnan.moviecatalog.core.databinding.ItemShowBinding
import com.romnan.moviecatalog.core.domain.model.tvseries.TvSeries

class TvSeriesAdapter : RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>() {

    private var tvSeriesList = ArrayList<TvSeries>()
    var onItemClick: ((TvSeries) -> Unit)? = null

    fun setData(tvSeries: List<TvSeries>?) {
        if (tvSeries == null) return
        tvSeriesList.clear()
        tvSeriesList.addAll(tvSeries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder =
        TvSeriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false)
        )

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        val data = tvSeriesList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = tvSeriesList.size


    inner class TvSeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemShowBinding.bind(itemView)
        private val context = itemView.context
        fun bind(data: TvSeries) {
            with(binding) {
                Glide.with(context)
                    .load(
                        String.format(
                            context.getString(R.string.image_base_url),
                            data.posterPath
                        )
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(ivItemPoster)

                tvItemTitle.text = data.name
                tvItemReleaseDate.text = data.firstAirDate
                tvItemOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(tvSeriesList[adapterPosition])
            }
        }
    }
}