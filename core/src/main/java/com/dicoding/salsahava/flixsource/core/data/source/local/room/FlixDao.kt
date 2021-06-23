package com.dicoding.salsahava.flixsource.core.data.source.local.room

import androidx.room.*
import com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlixDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE favorited = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movie_id = :movieId")
    fun getMovieById(movieId: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)
}