package com.jamesvanhallen.movierater.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jamesvanhallen.movierater.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val moviesViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moviesViewModel.movies.observeForever {
            Log.d("AAA", "movies sizeeee " + it.size)
        }
    }
}
