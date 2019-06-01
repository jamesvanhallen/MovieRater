package com.jamesvanhallen.movierater.model.database.movie

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movies: List<Movie>)

    @Query("SELECT * FROM movies ORDER BY " + Movie.RATIO + " DESC")
    fun fetchAll(): LiveData<List<Movie>>

    @Update
    suspend fun rateMovies(movie: List<Movie>)
}