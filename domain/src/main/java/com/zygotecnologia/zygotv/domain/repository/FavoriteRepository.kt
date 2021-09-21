package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(favorite: FavoriteDTO): Flow<Unit>

    suspend fun getAll(): Flow<List<FavoriteDTO>>
}