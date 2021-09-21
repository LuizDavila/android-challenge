package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.FavoritesUIModel
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO

object FavoritesUIMapper {





    fun map(favoritesList: List<FavoriteDTO>) = favoritesList.map {
       map(it)
    }

    fun map(favoriteDTO: FavoriteDTO) = favoriteDTO.run {
        FavoritesUIModel(id, name,posterPath)
    }
}