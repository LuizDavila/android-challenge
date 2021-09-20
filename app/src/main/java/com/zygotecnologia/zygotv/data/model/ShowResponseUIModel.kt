package com.zygotecnologia.zygotv.data.model

data class ShowResponseUIModel(
    val genres: List<GenreUIModel>?,
    val name: String?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val originalName: String?,
    val genreIds: List<Int>?,
    val voteCount: Int?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val season: List<SeasonUIModel>

)

data class SeasonUIModel(
    val seasonNumber: Int?,
    val posterPath: String?,
    val overview: String?
)
