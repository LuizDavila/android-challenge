package com.zygotecnologia.zygotv.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteDTO::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO
}