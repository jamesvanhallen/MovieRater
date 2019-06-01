package com.jamesvanhallen.movierater.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.source.MovieRepository
import com.jamesvanhallen.movierater.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import kotlin.random.Random

class MovieViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movies: LiveData<List<Movie>> = movieRepository.allMovies
    val snackLiveData = MutableLiveData<Long>()
    var rateJob: Job? = null

    fun onRatioChanged(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.rateMovies(listOf(movie))
    }

    fun rateRandomly() {
        if (rateJob == null || rateJob?.isActive == false) {
            rateJob = viewModelScope.launch(Dispatchers.IO) {
                val randomDelay = Random.nextLong(MIN_RANDOM_DELAY, MAX_RANDOM_DELAY)
                withContext(Dispatchers.Main) {
                    snackLiveData.postValue(randomDelay / ONE_SECOND)
                }
                delay(randomDelay)
                val movies = movies.value
                movies?.let {
                    it.forEach { movie ->
                        movie.ratio = Random.nextInt(MAX_RATIO).toFloat()
                    }
                    movieRepository.rateMovies(it)
                }
            }
            addCancellableJob(rateJob)
        } else {
            rateJob?.cancel()
            snackLiveData.postValue(0)
        }
    }

    companion object {
        private const val ONE_SECOND = 1000
        private const val MAX_RANDOM_DELAY = 10000L
        private const val MIN_RANDOM_DELAY = 3000L
        private const val MAX_RATIO = 5
    }
}