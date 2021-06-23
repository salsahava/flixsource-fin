package com.dicoding.salsahava.flixsource.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.domain.usecase.FlixUseCase

class FavoriteViewModel(private val flixUseCase: FlixUseCase) : ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Movie>> = flixUseCase.getFavoriteMovies().asLiveData()
}