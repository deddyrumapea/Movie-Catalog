package com.romnan.moviecatalog.ui.catalog.tvshows

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.romnan.moviecatalog.R
import kotlinx.android.synthetic.main.fragment_popular_shows.*

class PopTvShowsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_popular_shows, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PopTvShowsViewModel::class.java]

        val tvShowAdapter = PopTvShowsAdapter()

        with(rv_pop_show) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        viewModel.getPopTvShows()

        viewModel.popTvShows.observe(this, { tvShows ->
            tvShowAdapter.setShows(tvShows)
            Log.d(TAG, "onActivityCreated: ${tvShows.size}")
        })

        viewModel.isLoading.observe(this, {
            progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    companion object {
        private const val TAG = "PopTvShowsFragment"
    }
}