package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.PopularShowResponseUIModel
import com.zygotecnologia.zygotv.domain.entity.response.PopularShowResponse

object PopularShowResponseUIMapper {

    fun map(popularShowResponse: PopularShowResponse) = popularShowResponse.run {
        PopularShowResponseUIModel(page ?: 0, totalResults ?: 0,
            totalPages ?: 0, results ?: listOf())
    }
}