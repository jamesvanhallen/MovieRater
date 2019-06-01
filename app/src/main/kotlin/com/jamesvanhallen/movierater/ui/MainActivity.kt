package com.jamesvanhallen.movierater.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jamesvanhallen.movierater.R
import com.jamesvanhallen.movierater.extantions.replaceFragment
import com.jamesvanhallen.movierater.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.replaceFragment(MovieFragment()).commit()
        }
    }
}
