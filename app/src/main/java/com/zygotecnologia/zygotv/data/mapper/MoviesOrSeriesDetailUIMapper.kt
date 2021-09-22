package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesDetailUIModel
import com.zygotecnologia.zygotv.data.model.SeasonUIModel
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse
import com.zygotecnologia.zygotv.domain.entity.response.SeasonResponse

object MoviesOrSeriesDetailUIMapper {

    fun map(showResponseModel: MovieOrSeriesDetailResponse) = showResponseModel.run {
        MoviesOrSeriesDetailUIModel(
            GenreUIMapper.map(genres ?: listOf()), name ?: "", id, overview ?: "",
            posterPath ?: "", backdropPath ?: "",genreIds ?: listOf(), voteCount ?: 0,
            season?.map { mapSeason(it)  } ?: listOf()
        )
    }

    private fun mapSeason(seasonResponse: SeasonResponse) = seasonResponse.run {
        SeasonUIModel(seasonNumber?: 0, name,posterPath?:"", overview?: "")
    }

}