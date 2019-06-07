package com.jamesvanhallen.movierater.model.source

import androidx.lifecycle.LiveData
import com.jamesvanhallen.movierater.model.database.movie.Movie
import kotlinx.coroutines.Job

interface MovieRepository {

    val allMovies: LiveData<List<Movie>>

    suspend fun rateMovies(movies: List<Movie>)

    fun setupDefault(): Job?
}