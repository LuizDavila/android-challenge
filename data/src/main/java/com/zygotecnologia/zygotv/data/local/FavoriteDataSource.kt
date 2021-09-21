package com.zygotecnologia.zygotv.data.local

import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
class FavoriteDataSource(private val dao: FavoriteDAO): FavoriteSource {

    private val getAllChannel = ConflatedBroadcastChannel<List<FavoriteDTO>>()
    private val insertChannel = ConflatedBroadcastChannel<Unit>()


    override suspend fun getAll(): Flow<List<FavoriteDTO>> {
       getAllChannel.offer(dao.getAll())
        return getAllChannel.asFlow()
    }

    override suspend fun insert(favorite: FavoriteDTO): Flow<Unit> {
        insertChannel.offer(dao.insert(favorite))
        return insertChannel.asFlow()
    }


}