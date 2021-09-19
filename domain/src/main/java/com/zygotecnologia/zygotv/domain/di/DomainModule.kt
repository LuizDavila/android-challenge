package com.zygotecnologia.zygotv.domain.di

import com.zygotecnologia.zygotv.domain.usecase.FetchMoviesOrSeriesAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.fetchPopularShowsAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.fetchShowAsyncUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        FetchMoviesOrSeriesAsyncUseCase(
            repository = get()
        )
    }

    factory {
        fetchPopularShowsAsyncUseCase(
            repository = get()
        )
    }

    factory {
        fetchShowAsyncUseCase(
            repository = get()
        )
    }

}