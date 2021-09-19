package com.zygotecnologia.zygotv.data.model

import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse

data class PopularShowResponseUIModel(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<ShowResponse>
)
