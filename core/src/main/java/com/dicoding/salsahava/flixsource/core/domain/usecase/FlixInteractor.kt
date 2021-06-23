package com.dicoding.salsahava.flixsource.core.domain.usecase

import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.domain.repository.IFlixRepository

class FlixInteractor(private val flixRepository: IFlixRepository) : FlixUseCase {
    override fun getUpcomingMovies() =
        flixRepository.getUpcomingMovies()

    override fun getMovieDetail(movieId: Int) =
        flixRepository.getMovieDetail(movieId)

    override fun getFavoriteMovies() =
        flixRepository.getFavoriteMovies()

    override fun setMovieFavorite(movie: Movie, state: Boolean) =
        flixRepository.setMovieFavorite(movie, state)
}