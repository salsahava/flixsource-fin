package com.dicoding.salsahava.flixsource.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.salsahava.flixsource.core.data.Resource
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.domain.usecase.FlixUseCase

class MovieViewModel(private val flixUseCase: FlixUseCase) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> = flixUseCase.getUpcomingMovies().asLiveData()
}