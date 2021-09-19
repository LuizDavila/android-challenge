package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.GenreUIModel
import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.GenreResponse

object GenreResponseUIMapper {

    fun map(genreListResponse: GenreListResponse) = genreListResponse.genres?.map {
            map(it)
        } ?: listOf()

    fun map(genders: List<GenreResponse>) = genders.map {
        map(it)
    }

    private fun map(genreResponse: GenreResponse) = genreResponse.run {
        GenreUIModel(id,name ?: "")
    }

}