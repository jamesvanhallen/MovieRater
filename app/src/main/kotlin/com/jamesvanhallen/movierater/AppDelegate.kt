package com.jamesvanhallen.movierater

import android.app.Application
import com.jamesvanhallen.movierater.di.dataBaseModule
import com.jamesvanhallen.movierater.di.repositoryModule
import com.jamesvanhallen.movierater.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.DEBUG)
            } else {
                EmptyLogger()
            }
            androidContext(this@AppDelegate)
            modules(
                listOf(
                    dataBaseModule, repositoryModule, viewModelModule
                )
            )
        }
    }
}