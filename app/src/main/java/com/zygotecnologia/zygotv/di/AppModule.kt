package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.ui.detail.DetailViewModel
import com.zygotecnologia.zygotv.ui.main.favorites.FavoritesViewModel
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.MovieOrSeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MovieOrSeriesViewModel(useCase = get())
    }

    viewModel {
        DetailViewModel(
            useCase = get(),
            insertFavoriteUseCase = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            getAllFavoriteUseCase = get()

        )
    }

}