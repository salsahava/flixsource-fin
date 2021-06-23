package com.dicoding.salsahava.flixsource.favorites.di

import com.dicoding.salsahava.flixsource.favorites.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoriteViewModel(get()) }
}