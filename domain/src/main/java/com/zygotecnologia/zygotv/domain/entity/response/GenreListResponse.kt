package com.zygotecnologia.zygotv.domain.entity.response

data class GenreListResponse(
    val genres: List<GenreResponse>?
)

data class GenreResponse(
    val id: Int?,
    val name: String?
)