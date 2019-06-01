package com.jamesvanhallen.movierater.di

import com.jamesvanhallen.movierater.model.database.MoviesDataBase
import com.jamesvanhallen.movierater.model.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MovieRepository(get<MoviesDataBase>().movieDao)
    }
}