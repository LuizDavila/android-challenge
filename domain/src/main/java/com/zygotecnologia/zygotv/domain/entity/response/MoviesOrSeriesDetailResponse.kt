package com.zygotecnologia.zygotv.domain.entity.response

import com.google.gson.annotations.SerializedName

data class MovieOrSeriesDetailResponse(
    val genres: List<GenreResponse>?,
    val name: String?,
    val id: Int?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("seasons")
    val season: List<SeasonResponse>?,
    val title: String?
)

data class SeasonResponse(
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String?
)

