package com.romnan.showcatalog.ui.shows

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.PopShow
import kotlinx.android.synthetic.main.item_pop_show.view.*

abstract class PopShowAdapter : RecyclerView.Adapter<PopShowAdapter.ShowViewHolder>() {

    private var showsList = ArrayList<PopShow>()

    fun setShows(shows: List<PopShow>) {
        showsList.clear()
        showsList.addAll(shows)
        notifyDataSetChanged()
        Log.d(TAG, "setShows: ${showsList.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_pop_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = showsList[position]
        holder.bind(show)
        holder.itemView.setOnClickListener { openDetail(show.id, it) }
    }

    abstract fun openDetail(id: String?, view: View)

    override fun getItemCount(): Int = showsList.size

    class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(show: PopShow) {
            with(itemView) {
                // If it's a movie, use title and release date.
                // If it's a TV show, use name and first air date.
                val title = if (show.title != null) show.title else show.name
                val releaseDate =
                    if (show.releaseDate != null) show.releaseDate else show.firstAirDate

                text_pop_show_item_title.text = title
                text_pop_show_item_release_date.text = releaseDate
                text_pop_show_item_overview.text = show.overview

                Glide.with(context)
                    .load(
                        String.format(
                            context.getString(R.string.image_base_url),
                            show.posterPath
                        )
                    )
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_pop_show_item_poster)
            }
        }

    }

    companion object {
        private const val TAG = "PopShowsAdapter"
    }
}