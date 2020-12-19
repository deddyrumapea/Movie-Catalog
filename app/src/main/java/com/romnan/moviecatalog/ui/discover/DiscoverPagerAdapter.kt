package com.romnan.moviecatalog.ui.discover

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.ui.discover.movie.DiscoverMovieFragment
import com.romnan.moviecatalog.ui.discover.tvseries.DiscoverTvSeriesFragment

class DiscoverPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_series)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> DiscoverMovieFragment()
            1 -> DiscoverTvSeriesFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position]
    )
}