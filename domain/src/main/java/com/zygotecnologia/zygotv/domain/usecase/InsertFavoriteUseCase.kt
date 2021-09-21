package com.zygotecnologia.zygotv.domain.usecase

import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.domain.repository.FavoriteRepository

class InsertFavoriteUseCase(private val repository: FavoriteRepository) {

    suspend operator fun invoke(favorite: FavoriteDTO) =
        repository.insert(favorite)
}