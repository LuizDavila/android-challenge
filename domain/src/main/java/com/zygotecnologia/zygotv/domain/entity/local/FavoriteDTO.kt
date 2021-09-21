package com.zygotecnologia.zygotv.domain.entity.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorite")
@Parcelize
data class FavoriteDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    val posterPath: String?
) : Parcelable