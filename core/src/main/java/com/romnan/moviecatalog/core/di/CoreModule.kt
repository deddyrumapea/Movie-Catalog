package com.romnan.moviecatalog.core.di

import androidx.room.Room
import com.romnan.moviecatalog.core.data.MovieCatalogRepository
import com.romnan.moviecatalog.core.data.source.local.LocalDataSource
import com.romnan.moviecatalog.core.data.source.local.room.MovieCatalogDatabase
import com.romnan.moviecatalog.core.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.core.data.source.remote.api.ApiService
import com.romnan.moviecatalog.core.domain.repository.IMovieCatalogRepository
import com.romnan.moviecatalog.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieCatalogDatabase>().movieCatalogDao() }
    single {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("romnan".toCharArray())
        val factory = SupportFactory(passPhrase)

        Room.databaseBuilder(androidContext(), MovieCatalogDatabase::class.java, "MovieCatalog.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
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
    single<IMovieCatalogRepository> {
        MovieCatalogRepository(
            get(),
            get(),
            get()
        )
    }
}