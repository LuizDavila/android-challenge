package com.zygotecnologia.zygotv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite")
    fun getAll(): Flow<List<FavoriteDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteDTO)
}