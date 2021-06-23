package com.dicoding.salsahava.flixsource.core.domain.repository

import com.dicoding.salsahava.flixsource.core.data.Resource
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IFlixRepository {
    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getMovieDetail(movieId: Int): Flow<Resource<Movie>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}