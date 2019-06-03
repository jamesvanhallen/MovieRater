package com.jamesvanhallen.movierater.model.source

import androidx.lifecycle.LiveData
import com.jamesvanhallen.movierater.model.database.movie.Movie

interface MovieRepository {

    val allMovies: LiveData<List<Movie>>

    suspend fun rateMovies(movies: List<Movie>)
}