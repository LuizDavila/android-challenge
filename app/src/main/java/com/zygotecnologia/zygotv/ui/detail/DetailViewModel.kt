package com.zygotecnologia.zygotv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zygotecnologia.zygotv.data.mapper.MoviesOrSeriesDetailUIMapper
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesDetailUIModel
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.domain.usecase.FetchMoviesOrSeriesDetailAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.InsertFavoriteUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: FetchMoviesOrSeriesDetailAsyncUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val crashlytics: FirebaseCrashlytics
) : ViewModel() {

    private val _detailSeries = MediatorLiveData<MoviesOrSeriesDetailUIModel>()
    val detailSeries: LiveData<MoviesOrSeriesDetailUIModel>
        get() = _detailSeries

    private val _insert = MediatorLiveData<Unit>()
    val insert: LiveData<Unit>
        get() = _insert

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        crashlytics.recordException(exception)
        _loading.postValue(false)
    }

    fun fetchShowAsync(isMovie: Boolean, id: Int) {
        viewModelScope.launch(errorHandler) {
            useCase(isMovie, id)
                .map { MoviesOrSeriesDetailUIMapper.map(it) }
                .onStart { _loading.postValue(true) }
                .collect {
                    _detailSeries.postValue(it)
                    _loading.postValue(false)
                }

        }
    }

    fun insert(favorite: FavoriteDTO) {
        viewModelScope.launch(errorHandler) {
            insertFavoriteUseCase(favorite)
                .onStart { _loading.postValue(true) }
                .collect {
                    _insert.postValue(it)
                    _loading.postValue(false)
                }
        }
    }
}