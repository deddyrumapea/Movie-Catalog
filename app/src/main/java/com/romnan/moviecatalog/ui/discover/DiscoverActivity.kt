package com.romnan.moviecatalog.ui.discover

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.ui.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_discover.*

class DiscoverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        val sectionsPagerAdapter = DiscoverPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

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
        startActivity(Intent(this, FavoriteActivity::class.java))


    private fun showAboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.about))
        builder.setMessage(getString(R.string.about_message))
        builder.setCancelable(true)
        builder.create().show()
    }
}