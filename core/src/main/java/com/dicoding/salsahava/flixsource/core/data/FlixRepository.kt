package com.dicoding.salsahava.flixsource.core.data

import com.dicoding.salsahava.flixsource.core.data.source.local.LocalDataSource
import com.dicoding.salsahava.flixsource.core.data.source.remote.RemoteDataSource
import com.dicoding.salsahava.flixsource.core.data.source.remote.network.ApiResponse
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieDetail
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieItem
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.domain.repository.IFlixRepository
import com.dicoding.salsahava.flixsource.core.utils.AppExecutors
import com.dicoding.salsahava.flixsource.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FlixRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IFlixRepository {

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getAllMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getUpcomingMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDetail>() {
            override fun loadFromDB(): Flow<Movie> =
                localDataSource.getMovieById(movieId).map {
                    DataMapper.mapMovieDetailEntityToDomain(it)
                }

            override fun shouldFetch(data: Movie?): Boolean =
                data?.genres == ""

            override suspend fun createCall(): Flow<ApiResponse<MovieDetail>> =
                remoteDataSource.getMovieDetail(movieId)

            override suspend fun saveCallResult(data: MovieDetail) {
                val genres = ArrayList<String>()

                for (genre in data.genres) {
                    genres.add(genre.name)
                }

                val movie = DataMapper.mapMovieDetailResponsesToEntities(data, genres)
                localDataSource.insertMovie(movie)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }
}