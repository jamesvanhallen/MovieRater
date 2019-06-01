package com.jamesvanhallen.movierater.ui

import androidx.lifecycle.MutableLiveData
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.repository.MovieRepository
import com.jamesvanhallen.movierater.ui.base.BaseViewModel

class MainViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movies = MutableLiveData<List<Movie>>()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        val job = launchLoadingErrorJob {
            val moviesToShow = movieRepository.getAll()
            movies.value = moviesToShow
        }
        addCancellableJob(job)
    }
}