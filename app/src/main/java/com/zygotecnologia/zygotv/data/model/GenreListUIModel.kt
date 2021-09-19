package com.zygotecnologia.zygotv.data.model

data class GenreListUIModel(
    val genres: List<GenreUIModel>
)

data class GenreUIModel(
    val id: Int?,
    val name: String
)