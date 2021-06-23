package com.dicoding.salsahava.flixsource.core.data.source.local

import com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity
import com.dicoding.salsahava.flixsource.core.data.source.local.room.FlixDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mFlixDao: FlixDao) {
    fun getAllMovies(): Flow<List<MovieEntity>> =
        mFlixDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> =
        mFlixDao.getFavoriteMovies()

    fun getMovieById(movieId: Int): Flow<MovieEntity> =
        mFlixDao.getMovieById(movieId)

    suspend fun insertMovies(movies: List<MovieEntity>) =
        mFlixDao.insertMovies(movies)

    suspend fun insertMovie(movie: MovieEntity) =
        mFlixDao.insertMovie(movie)

    fun updateMovieDetail(movie: MovieEntity) =
        mFlixDao.updateMovie(movie)

    fun setMovieFavorite(
        movie: MovieEntity,
        newState: Boolean
    ) {
        movie.favorited = newState
        mFlixDao.updateMovie(movie)
    }
}