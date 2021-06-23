package com.dicoding.salsahava.flixsource.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.salsahava.flixsource.databinding.ActivitySplashScreenBinding
import com.dicoding.salsahava.flixsource.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenActivityBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenActivityBinding.root)

        window.insetsController?.hide(WindowInsets.Type.statusBars())

        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}