package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.SeasonUIModel
import com.zygotecnologia.zygotv.data.model.ShowResponseUIModel
import com.zygotecnologia.zygotv.domain.entity.response.SeasonResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse

object ShowResponseUIMapper {

    fun map(showResponseModel: ShowResponse) = showResponseModel.run {
        ShowResponseUIModel(
            GenreResponseUIMapper.map(genres ?: listOf()), name ?: "", id, overview ?: "",
            posterPath ?: "", originalName ?: "", genreIds ?: listOf(),
            voteCount ?: 0, backdropPath ?: "", originalLanguage ?: "",
            season?.map { mapSeason(it)  } ?: listOf()
        )
    }

    fun mapSeason(seasonResponse: SeasonResponse) = seasonResponse.run {
        SeasonUIModel(seasonNumber?: 0, posterPath?:"", overview?: "")
    }

}