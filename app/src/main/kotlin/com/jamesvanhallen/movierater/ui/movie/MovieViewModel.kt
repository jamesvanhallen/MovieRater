package com.jamesvanhallen.movierater.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.source.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

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
                snackLiveData.postValue(randomDelay / ONE_SECOND)
                delay(randomDelay)
                setRandomRatio()
            }
        } else {
            rateJob?.cancel()
            snackLiveData.postValue(0)
        }
    }

    private suspend fun setRandomRatio() {
        movieRepository.allMovies.value?.let {
            it.forEach { movie ->
                movie.ratio = Random.nextInt(MAX_RATIO).toFloat()
            }
            movieRepository.rateMovies(it)
        }
    }

    companion object {
        private const val ONE_SECOND = 1000
        private const val MAX_RANDOM_DELAY = 10000L
        private const val MIN_RANDOM_DELAY = 3000L
        private const val MAX_RATIO = 5
    }
}