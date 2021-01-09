package com.romnan.moviecatalog.discover

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.databinding.ActivityDiscoverBinding

class DiscoverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = DiscoverPagerAdapter(this, supportFragmentManager)
        binding.vpDiscover.adapter = sectionsPagerAdapter
        binding.tlDiscover.setupWithViewPager(binding.vpDiscover)

        // Action bar adjustments
        supportActionBar?.elevation = 0f
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimaryDark
                )
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> openFavoriteActivity()
            R.id.menu_about -> showAboutDialog()
        }
        return true
    }

    private fun openFavoriteActivity() =
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("moviecatalog://favorite")
            )
        )

    private fun showAboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.about))
        builder.setMessage(getString(R.string.about_message))
        builder.setCancelable(true)
        builder.create().show()
    }
}