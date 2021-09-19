package com.zygotecnologia.zygotv.data.di

import com.zygotecnologia.zygotv.data.remote.TmdbApi
import com.zygotecnologia.zygotv.data.remote.TmdbApi.Companion.TMDB_BASE_URL
import com.zygotecnologia.zygotv.data.remote.TmdbDataSource
import com.zygotecnologia.zygotv.data.remote.TmdbSource
import com.zygotecnologia.zygotv.data.repository.TmdbDataRepository
import com.zygotecnologia.zygotv.domain.repository.TmdbRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import org.koin.android.BuildConfig
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
    factory { createOkHttpClient() }

    single {
        createWebService<TmdbApi>(
            okHttpClient = get(),
            url = TMDB_BASE_URL
        )
    }
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
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