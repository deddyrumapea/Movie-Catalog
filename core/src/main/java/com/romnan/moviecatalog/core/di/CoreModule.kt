package com.romnan.moviecatalog.core.di

import androidx.room.Room
import com.romnan.moviecatalog.core.BuildConfig
import com.romnan.moviecatalog.core.data.MovieCatalogRepository
import com.romnan.moviecatalog.core.data.source.local.LocalDataSource
import com.romnan.moviecatalog.core.data.source.local.room.MovieCatalogDatabase
import com.romnan.moviecatalog.core.data.source.remote.RemoteDataSource
import com.romnan.moviecatalog.core.data.source.remote.api.ApiService
import com.romnan.moviecatalog.core.domain.repository.IMovieCatalogRepository
import com.romnan.moviecatalog.core.util.AppExecutors
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
    factory { get<MovieCatalogDatabase>().movieCatalogDao() }
    single {
        val databaseName = BuildConfig.DATABASE_NAME
        val phrase = BuildConfig.SQL_CIPHER_PHRASE.toCharArray()

        val passPhrase: ByteArray = SQLiteDatabase.getBytes(phrase)
        val factory = SupportFactory(passPhrase)

        Room.databaseBuilder(androidContext(), MovieCatalogDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(
                BuildConfig.TMDB_HOSTNAME,
                BuildConfig.TMDB_PIN1,
                BuildConfig.TMDB_PIN2,
                BuildConfig.TMDB_PIN3,
                BuildConfig.TMDB_PIN4
            )
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
            .baseUrl(BuildConfig.TMDB_BASE_URL)
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