package com.romnan.moviecatalog.splashscreen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.discover.DiscoverActivity
import gr.net.maroulis.library.EasySplashScreen

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val easySplashScreen = EasySplashScreen(this)
            .withFullScreen()
            .withTargetActivity(DiscoverActivity::class.java)
            .withSplashTimeOut(3000)
            .withBackgroundColor(getColor(R.color.colorPrimaryDark))
            .withLogo(R.mipmap.ic_launcher)
            .withAfterLogoText(getString(R.string.app_name))

        easySplashScreen.afterLogoTextView.setTextColor(Color.WHITE)

        setContentView(easySplashScreen.create())
        supportActionBar?.hide()
    }
}