package com.dicoding.salsahava.flixsource.ui.detail

import androidx.lifecycle.*
import com.dicoding.salsahava.flixsource.core.data.Resource
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.domain.usecase.FlixUseCase

class DetailViewModel(private val flixUseCase: FlixUseCase) : ViewModel() {

    private var itemId = MutableLiveData<Int>()

    fun setSelectedItem(itemId: Int) {
        this.itemId.value = itemId
    }

    var movie: LiveData<Resource<Movie>> = Transformations.switchMap(itemId) { mItemId ->
        flixUseCase.getMovieDetail(mItemId).asLiveData()
    }

    fun setMovieFavorite() {
        val movieResource = movie.value

        if (movieResource != null) {
            val movie = movieResource.data

            if (movie != null) {
                val newState = !movie.favorited
                flixUseCase.setMovieFavorite(movie, newState)
            }
        }
    }
}