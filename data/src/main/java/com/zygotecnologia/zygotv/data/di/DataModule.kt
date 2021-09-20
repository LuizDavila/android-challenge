package com.zygotecnologia.zygotv.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.zygotecnologia.zygotv.data.R
import com.zygotecnologia.zygotv.data.local.FavoritesDatabase
import com.zygotecnologia.zygotv.data.remote.TmdbApi
import com.zygotecnologia.zygotv.data.remote.TmdbDataSource
import com.zygotecnologia.zygotv.data.remote.TmdbSource
import com.zygotecnologia.zygotv.data.repository.TmdbDataRepository
import com.zygotecnologia.zygotv.data.util.AddKeyRequestInterceptor
import com.zygotecnologia.zygotv.domain.repository.TmdbRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@FlowPreview
@ExperimentalCoroutinesApi
val dataModule = module {

    factory<TmdbSource> {
        TmdbDataSource(api = get())
    }
}

val repositoryModule = module{

    factory<TmdbRepository> {
        TmdbDataRepository(source = get())
    }
}

val remoteModule = module {
    factory { createOkHttpClient(get()) }

    single {
        createWebService<TmdbApi>(
            okHttpClient = get(),
            url = androidContext().getString(R.string.TMDB_BASE_URL)
        )
    }
}

val databaseModule = module {

    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application, FavoritesDatabase::class.java, "favoriteDb")
        .fallbackToDestructiveMigration()
        .build()

    fun provideFavouritesDao(database: FavoritesDatabase) = database.favoriteDAO()

    single { provideDatabase(androidApplication()) }
    single { provideFavouritesDao(get()) }
}

private fun createOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(AddKeyRequestInterceptor(context))
        .apply {
            if (BuildConfig.DEBUG)
                connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
        }
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
}

private inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}