package com.romnan.favorite

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.romnan.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)

        val sectionsPagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        vp_discover.adapter = sectionsPagerAdapter
        tl_discover.setupWithViewPager(vp_discover)

        // Action bar adjustments
        supportActionBar?.elevation = 0f
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimaryDark)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}