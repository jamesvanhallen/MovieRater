package com.jamesvanhallen.movierater.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.jamesvanhallen.movierater.CoroutinesTestRule
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.source.MovieRepository
import com.jamesvanhallen.movierater.testObserver
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class MovieViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var movieRepository: MovieRepository

    @Test
    fun `Emit empty data`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        `when`(movieRepository.allMovies).thenReturn(MutableLiveData<List<Movie>>())
        val viewModel = MovieViewModel(movieRepository)
        val testObserver = viewModel.movies.testObserver()

        Truth.assert_()
            .that(testObserver.observedValues)
            .isEqualTo(emptyList<Movie>())
    }

    @Test
    fun `Emit list of to items`() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val movieLiveData = MutableLiveData<List<Movie>>()
        `when`(movieRepository.allMovies).thenReturn(movieLiveData)
        val viewModel = MovieViewModel(movieRepository)
        val testObserver = viewModel.movies.testObserver()
        val testData = listOf(
            Movie(1, "test1", 0.5f, ""),
            Movie(2, "test2", 1f, "")
        )
        movieLiveData.postValue(testData)

        Truth.assert_()
            .that(testObserver.observedValues)
            .isEqualTo(listOf(testData))
    }
}