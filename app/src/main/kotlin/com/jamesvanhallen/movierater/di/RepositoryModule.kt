package com.jamesvanhallen.movierater.di

import com.jamesvanhallen.movierater.model.database.MoviesDataBase
import com.jamesvanhallen.movierater.model.source.MovieRepository
import com.jamesvanhallen.movierater.model.source.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MovieRepositoryImpl(get<MoviesDataBase>().movieDao) as MovieRepository
    }
}