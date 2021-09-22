package com.zygotecnologia.zygotv.data.model

data class MoviesOrSeriesDetailUIModel(
    val genres: List<GenreUIModel>?,
    val name: String?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val voteCount: Int?,
    val season: List<SeasonUIModel>
)

data class SeasonUIModel(
    val seasonNumber: Int?,
    val name: String?,
    val posterPath: String?,
    val overview: String?
)
