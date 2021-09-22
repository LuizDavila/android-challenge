package com.zygotecnologia.zygotv.domain.di

import com.zygotecnologia.zygotv.domain.usecase.FetchMoviesOrSeriesAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.FetchMoviesOrSeriesDetailAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.GetAllFavoriteUseCase
import com.zygotecnologia.zygotv.domain.usecase.InsertFavoriteUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        FetchMoviesOrSeriesAsyncUseCase(
            repository = get()
        )
    }

    factory {
        FetchMoviesOrSeriesDetailAsyncUseCase(
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