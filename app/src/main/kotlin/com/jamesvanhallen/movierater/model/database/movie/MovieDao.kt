package com.jamesvanhallen.movierater.model.database.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class MovieDao {

    @Insert
    abstract fun insert(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    abstract fun fetchAll(): List<Movie>

    @Update
    abstract fun rateMovie(movie: Movie)
}