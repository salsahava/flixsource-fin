package com.dicoding.salsahava.flixsource.core.data.source.remote

import android.util.Log
import com.dicoding.salsahava.flixsource.core.data.source.remote.network.ApiResponse
import com.dicoding.salsahava.flixsource.core.data.source.remote.network.ApiService
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieDetail
import com.dicoding.salsahava.flixsource.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getUpcomingMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return flow {
            try {
                val response = apiService.getUpcomingMovieList()
                val data = response.upcomingMoviesList

                if (data.isNotEmpty()) emit(ApiResponse.Success(data))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(
                    TAG,
                    e.toString()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieDetail>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(
                    TAG,
                    e.toString()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "RemoteDataSource"
    }
}