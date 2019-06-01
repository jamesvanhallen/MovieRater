package com.jamesvanhallen.movierater.model.repository

import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.database.movie.MovieDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class MovieRepository(private val movieDao: MovieDao) {

    private val default = createDefaults()

    @Suppress("MagicNumber")
    private fun createDefaults(): List<Movie> {
        return listOf(
            Movie(1, "pulp fiction", 0, ""),
            Movie(2, "save private ryan", 0, ""),
            Movie(3, "godzilla", 0, ""),
            Movie(4, "spiderman", 0, "")
        )
    }

    suspend fun getAll(): List<Movie> {
        return withContext(Dispatchers.IO) {
            movieDao.fetchAll()
        }
    }

    fun rateMovie(movie: Movie): Job? {
        return GlobalScope.launch {
            movieDao.rateMovie(movie)
        }
    }

    fun setupDefault(): Job? {
        return GlobalScope.launch {
            movieDao.insert(default)
        }
    }
}