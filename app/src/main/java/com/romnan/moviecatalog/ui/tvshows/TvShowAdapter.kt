package com.romnan.moviecatalog.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.TvShow
import com.romnan.moviecatalog.ui.detail.tvshows.TvShowDetailActivity
import kotlinx.android.synthetic.main.activity_tv_show_detail.view.*
import kotlinx.android.synthetic.main.item_tv_show.view.*

class TvShowAdapter :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val tvShowsList = ArrayList<TvShow>()

    fun setTvShows(tvShows: ArrayList<TvShow>) {
        tvShowsList.clear()
        tvShowsList.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowsList[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int =
        tvShowsList.size

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with(itemView){
                text_tv_show_item_title.text = tvShow.title
                text_tv_show_item_genre.text = tvShow.genre
                text_tv_show_item_overview.text = tvShow.overview

                setOnClickListener{
                    val intent = Intent(context, TvShowDetailActivity::class.java).apply {
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, tvShow.id)
                    }
                    context.startActivity(intent)
                }

                Glide.with(context)
                    .load(tvShow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .into(image_tv_show_item_poster)
            }
        }

    }
}