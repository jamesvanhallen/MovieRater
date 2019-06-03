package com.jamesvanhallen.movierater.model.source

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.database.movie.MovieDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {

    private val default = createDefaults()

    override val allMovies: LiveData<List<Movie>> = movieDao.fetchAll()

    @WorkerThread
    override suspend fun rateMovies(movies: List<Movie>) = movieDao.rateMovies(movies)

    fun setupDefault(): Job? {
        return GlobalScope.launch {
            movieDao.insert(default)
        }
    }

    @Suppress("MagicNumber")
    private fun createDefaults(): List<Movie> {
        return listOf(
            Movie(
                1, "Oblivion", 0f,
                "https://anakin022.files.wordpress.com/2012/06/tesivoblivion_banner.jpg"
            ),
            Movie(
                2, "Captain America", 0f,
                "https://i.pinimg.com/originals/e0/02/70/e00270957104fdb69e4622633886ffd5.jpg"
            ),
            Movie(
                3, "Star Wars", 0f,
                "https://i2.wp.com/www.heyuguys.com/images/2015/10/star-wars-the-force-awakens-poster-landscape.jpg?fit=1536%2C864"
            ),
            Movie(
                5, "Avatar", 0f,
                "https://cdn.fishki.net/upload/post/201506/02/1552702/0_b7562_ad86ea89_xl.jpg"
            ),
            Movie(
                6, "Thor", 0f,
                "https://i.pinimg.com/originals/c5/06/fc/c506fc6b9746e41ff8bc85ccdf48c7ba.jpg"
            ),
            Movie(
                7, "Dark Knight", 0f,
                "https://www.m2ksys.com/extimages/2x2_Movie_Poster4.jpg"
            ),
            Movie(
                8, "Godzilla", 0f,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK8g7nZ6k598F8c0Vb1F43SgqB3BebBdriqwdLQoXj1taRP2np"
            ),
            Movie(
                9, "BattleShip Island", 0f,
                "https://film-book.com/wp-content/uploads/2017/07/the-battleship-island-movie-poster-01-600x350.jpg"
            ),
            Movie(
                10, "The Dark Knight Rises", 0f,
                "https://cdn.thearthunters.com/wp-content/uploads/old/201205272/4.jpg"
            )
        )
    }
}