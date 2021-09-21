package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.data.local.FavoriteSource
import com.zygotecnologia.zygotv.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteDataRepository(private val source: FavoriteSource): FavoriteRepository {

    override suspend fun insert(favorite: FavoriteDTO): Flow<Unit> =
        source.insert(favorite)


    override suspend fun getAll(): Flow<List<FavoriteDTO>> =
        source.getAll()

}