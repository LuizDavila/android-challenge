package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.repository.FavoriteRepository

class GetAllFavoriteUseCase(private val repository: FavoriteRepository) {

    suspend operator fun invoke() =
        repository.getAll()
}