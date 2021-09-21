package com.zygotecnologia.zygotv.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteDTO)
}