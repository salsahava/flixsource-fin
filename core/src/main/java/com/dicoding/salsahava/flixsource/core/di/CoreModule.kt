package com.dicoding.salsahava.flixsource.core.di

import androidx.room.Room
import com.dicoding.salsahava.flixsource.core.data.FlixRepository
import com.dicoding.salsahava.flixsource.core.data.source.local.LocalDataSource
import com.dicoding.salsahava.flixsource.core.data.source.local.room.FlixDatabase
import com.dicoding.salsahava.flixsource.core.data.source.remote.RemoteDataSource
import com.dicoding.salsahava.flixsource.core.data.source.remote.network.ApiService
import com.dicoding.salsahava.flixsource.core.domain.repository.IFlixRepository
import com.dicoding.salsahava.flixsource.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FlixDatabase>().flixDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FlixDatabase::class.java,
            "Flix.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFlixRepository> {
        FlixRepository(
            get(),
            get(),
            get()
        )
    }
}