package com.zygotecnologia.zygotv.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.mapper.ShowResponseUIMapper
import com.zygotecnologia.zygotv.data.model.ShowResponseUIModel
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.domain.usecase.FetchShowAsyncUseCase
import com.zygotecnologia.zygotv.domain.usecase.InsertFavoriteUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val useCase: FetchShowAsyncUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private val _detailSeries = MediatorLiveData<ShowResponseUIModel>()
    val detailSeries: LiveData<ShowResponseUIModel>
        get() = _detailSeries

    private val _insert = MediatorLiveData<Unit>()
    val insert: LiveData<Unit>
        get() = _insert

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val errorHandler = CoroutineExceptionHandler { _, exception ->
//        Timber.e(exception)
//        crashlytics.recordException(exception)
        _loading.postValue(false)
    }

    fun fetchShowAsync(apiKey: String, id: Int) {
        viewModelScope.launch(errorHandler) {
            useCase(apiKey, id)
                .map { ShowResponseUIMapper.map(it) }
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