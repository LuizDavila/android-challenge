package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.mapper.ShowUIMapper
import com.zygotecnologia.zygotv.data.model.SeriesUIModel
import com.zygotecnologia.zygotv.domain.usecase.FetchMoviesOrSeriesAsyncUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MovieOrSeriesViewModel(
    private val useCase: FetchMoviesOrSeriesAsyncUseCase
) : ViewModel() {

    private val _moviesOrSeries = MediatorLiveData<SeriesUIModel>()
    val moviesOrSeries: LiveData<SeriesUIModel>
        get() = _moviesOrSeries

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val errorHandler = CoroutineExceptionHandler { _, exception ->
//        Timber.e(exception)
//        crashlytics.recordException(exception)
        _loading.postValue(false)
    }

    fun fetchMoviesOrSeriesAsync(isMovie: Boolean) {
        viewModelScope.launch(errorHandler) {
            useCase(isMovie)
                .map { ShowUIMapper.map(it.first, it.second) }
                .onStart { _loading.postValue(true) }
                .collect {
                    _moviesOrSeries.postValue(it)
                    _loading.postValue(false)
                }
        }
    }
}