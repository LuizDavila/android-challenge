package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.GenreUIModel
import com.zygotecnologia.zygotv.domain.entity.response.GenreResponse

object GenreUIMapper {

    fun map(genres: List<GenreResponse>) = genres.map {
        map(it)
    }

    private fun map(genreResponse: GenreResponse) = genreResponse.run {
        GenreUIModel(id,name ?: "")
    }

}