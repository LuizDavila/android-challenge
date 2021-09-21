package com.zygotecnologia.zygotv.domain.di

import com.zygotecnologia.zygotv.domain.usecase.*
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
        FetchShowAsyncUseCase(
            repository = get()
        )
    }

    factory {
        InsertFavoriteUseCase(
            repository = get()
        )
    }

    factory {
        GetAllFavoriteUseCase(
            repository = get()
        )
    }

}