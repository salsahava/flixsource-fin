package com.dicoding.salsahava.flixsource.core.utils

import com.dicoding.salsahava.flixsource.core.data.source.local.entity.MovieEntity
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieDetail
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieItem
import com.dicoding.salsahava.flixsource.core.domain.model.Movie
import com.dicoding.salsahava.flixsource.core.utils.formatter.DetailFormatter

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieItem>): List<MovieEntity> {
        val movieList =
            ArrayList<MovieEntity>()

        input.map {
            val movie = MovieEntity(
                it.id,
                it.posterPath,
                it.title,
                it.releaseDate,
                it.overview
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDetailResponsesToEntities(
        input: MovieDetail,
        genres: ArrayList<String>
    ): MovieEntity =
        MovieEntity(
            input.id,
            input.posterPath,
            input.title,
            input.releaseDate,
            DetailFormatter.genresFormatter(genres),
            input.runtime,
            input.voteAverage,
            input.overview
        )

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                it.movieId,
                it.poster,
                it.title,
                it.releaseDate,
                it.genres,
                it.duration,
                it.score,
                it.description,
                it.favorited
            )
        }

    fun mapMovieDetailEntityToDomain(input: MovieEntity): Movie =
        Movie(
            input.movieId,
            input.poster,
            input.title,
            input.releaseDate,
            input.genres,
            input.duration,
            input.score,
            input.description,
            input.favorited
        )

    fun mapMovieDomainToEntity(input: Movie) =
        MovieEntity(
            input.movieId,
            input.poster,
            input.title,
            input.releaseDate,
            input.genres,
            input.duration,
            input.score,
            input.description
        )
}