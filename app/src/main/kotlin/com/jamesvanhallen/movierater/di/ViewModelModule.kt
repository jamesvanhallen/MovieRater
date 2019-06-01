package com.jamesvanhallen.movierater.di

import com.jamesvanhallen.movierater.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}