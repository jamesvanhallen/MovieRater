package com.jamesvanhallen.movierater

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.jamesvanhallen.movierater.di.dataBaseModule
import com.jamesvanhallen.movierater.di.repositoryModule
import com.jamesvanhallen.movierater.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level

class MovieRaterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            } else {
                EmptyLogger()
            }
            androidContext(this@MovieRaterApplication)
            modules(
                listOf(
                    dataBaseModule, repositoryModule, viewModelModule
                )
            )
        }
    }
}