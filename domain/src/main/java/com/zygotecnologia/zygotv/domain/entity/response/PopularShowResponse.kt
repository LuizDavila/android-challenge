package com.zygotecnologia.zygotv.domain.entity.response

import com.google.gson.annotations.SerializedName

data class PopularShowResponse(
    val page: Int?,
    val results: List<ShowResponse>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
)