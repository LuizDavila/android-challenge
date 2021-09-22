package com.zygotecnologia.zygotv.domain.entity.response

import com.google.gson.annotations.SerializedName

data class MoviesOrSeriesResponse(
    val page: Int?,
    val results: List<MovieOrSeriesDetailResponse>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
)