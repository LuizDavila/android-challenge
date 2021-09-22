package com.zygotecnologia.zygotv.ui.main.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zygotecnologia.zygotv.data.mapper.FavoritesUIMapper
import com.zygotecnologia.zygotv.data.model.FavoritesUIModel
import com.zygotecnologia.zygotv.domain.usecase.GetAllFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase
) : ViewModel() {


    private val _getAll = MediatorLiveData<List<FavoritesUIModel>>()
    val getAll: LiveData<List<FavoritesUIModel>>
        get() = _getAll

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoriteUseCase()
                .map { FavoritesUIMapper.map(it) }
                .onStart { _loading.postValue(true) }
                .collect {
                    _getAll.postValue(it)
                    _loading.postValue(false)
                }

        }
    }

}