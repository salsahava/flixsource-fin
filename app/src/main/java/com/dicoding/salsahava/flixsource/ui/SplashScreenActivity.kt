package com.dicoding.salsahava.flixsource.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.salsahava.flixsource.databinding.ActivitySplashScreenBinding
import com.dicoding.salsahava.flixsource.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenActivityBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenActivityBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) window.insetsController?.hide(
            WindowInsets.Type.statusBars()
        )
        else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}