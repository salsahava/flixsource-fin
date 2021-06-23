package com.dicoding.salsahava.flixsource.core.data.source.remote.network

import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieDetail
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        // TODO
        private const val API_KEY = "You can find it in local.properties"
    }

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovieList(): MovieResponse

    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail
}