package com.romnan.moviecatalog.ui.detail.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.romnan.moviecatalog.R

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}