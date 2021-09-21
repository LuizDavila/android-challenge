package com.zygotecnologia.zygotv.data.local

import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import kotlinx.coroutines.flow.Flow

interface FavoriteSource {

    suspend fun getAll(): Flow<List<FavoriteDTO>>

    suspend fun insert(favorite: FavoriteDTO): Flow<Unit>
}