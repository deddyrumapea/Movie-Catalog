package com.romnan.moviecatalog.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.romnan.moviecatalog.core.R
import com.romnan.moviecatalog.core.databinding.ItemShowBinding
import com.romnan.moviecatalog.core.presentation.MoviePresentation

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var moviesList = ArrayList<MoviePresentation>()
    var onItemClick: ((MoviePresentation) -> Unit)? = null

    fun setData(movies: List<MoviePresentation>?) {
        if (movies == null) return
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_show, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = moviesList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = moviesList.size


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemShowBinding.bind(itemView)
        private val context = itemView.context
        fun bind(data: MoviePresentation) {
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

                tvItemTitle.text = data.title
                tvItemReleaseDate.text = data.releaseDate
                tvItemOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(moviesList[adapterPosition])
            }
        }
    }
}