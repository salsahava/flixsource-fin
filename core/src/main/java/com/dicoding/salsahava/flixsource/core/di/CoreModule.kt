package com.dicoding.salsahava.flixsource.core.di

import androidx.room.Room
import com.dicoding.salsahava.flixsource.core.data.FlixRepository
import com.dicoding.salsahava.flixsource.core.data.source.local.LocalDataSource
import com.dicoding.salsahava.flixsource.core.data.source.local.room.FlixDatabase
import com.dicoding.salsahava.flixsource.core.data.source.remote.RemoteDataSource
import com.dicoding.salsahava.flixsource.core.data.source.remote.network.ApiService
import com.dicoding.salsahava.flixsource.core.domain.repository.IFlixRepository
import com.dicoding.salsahava.flixsource.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("havafs".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            FlixDatabase::class.java,
            "Flix.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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