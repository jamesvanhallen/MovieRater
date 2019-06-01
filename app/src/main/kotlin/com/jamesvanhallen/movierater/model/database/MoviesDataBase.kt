package com.jamesvanhallen.movierater.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.model.database.movie.MovieDao

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDataBase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        const val MOVIES_TABLE_NAME = "movies"
        const val MOVIES_DATABASE_NAME = "movies_database"
    }
}