package com.zygotecnologia.zygotv.di

import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.MovieOrSeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MovieOrSeriesViewModel(useCase = get())
    }

}