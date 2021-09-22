package com.zygotecnologia.zygotv.di

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zygotecnologia.zygotv.ui.detail.DetailViewModel
import com.zygotecnologia.zygotv.ui.main.favorites.FavoritesViewModel
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.MoviesOrSeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { crashlytics() }

    viewModel {
        MoviesOrSeriesViewModel(useCase = get(), crashlytics = get())
    }

    viewModel {
        DetailViewModel(
            useCase = get(),
            insertFavoriteUseCase = get(),
            crashlytics = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            getAllFavoriteUseCase = get()
        )
    }

}

private fun crashlytics() = FirebaseCrashlytics.getInstance()