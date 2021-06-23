package com.dicoding.salsahava.flixsource.di

import com.dicoding.salsahava.flixsource.core.domain.usecase.FlixInteractor
import com.dicoding.salsahava.flixsource.core.domain.usecase.FlixUseCase
import com.dicoding.salsahava.flixsource.ui.detail.DetailViewModel
import com.dicoding.salsahava.flixsource.ui.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FlixUseCase> { FlixInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}