package com.jamesvanhallen.movierater.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jamesvanhallen.movierater.model.database.MoviesDataBase
import com.jamesvanhallen.movierater.model.source.MovieRepository
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            MoviesDataBase::class.java,
            MoviesDataBase.MOVIES_DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    get<MovieRepository>().setupDefault()
                }
            })
            .fallbackToDestructiveMigration()
            .build()
    }
}