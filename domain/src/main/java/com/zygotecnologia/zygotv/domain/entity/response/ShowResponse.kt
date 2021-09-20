package com.zygotecnologia.zygotv.domain.entity.response

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    val genres: List<GenreResponse>?,
    val name: String?,
    val id: Int?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    val season: List<SeasonResponse>?
)

data class SeasonResponse(
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String?
)

