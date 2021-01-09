package com.romnan.favorite

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.romnan.favorite.databinding.ActivityFavoriteBinding
import com.romnan.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules


class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val sectionsPagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        binding.vpFavorite.adapter = sectionsPagerAdapter
        binding.tlFavorite.setupWithViewPager(binding.vpFavorite)

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