package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.CarrouselMoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesByGenderUIModel
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.GenreResponse
import com.zygotecnologia.zygotv.domain.entity.response.MovieOrSeriesDetailResponse

object MoviesOrSeriesUIMapper {

    fun map(genderList: GenreListResponse, list: List<MovieOrSeriesDetailResponse>) = MoviesOrSeriesByGenderUIModel(
        getTopShow(list),
        mapSeriesListByGender(genderList.genres, list)
    )

    private fun mapSeriesListByGender(genderList: List<GenreResponse>?, list: List<MovieOrSeriesDetailResponse>) =
        genderList?.distinct()?.map { gender ->
            MoviesOrSeriesUIModel(
                gender.name ?: "",
                mapListItemSeries(gender, list)
            )
        }?.filter { it.seriesList.isNotEmpty() }


    private fun getTopShow(list: List<MovieOrSeriesDetailResponse>) =
        list.maxByOrNull { it.voteCount ?: 0 }?.run {
            CarrouselMoviesOrSeriesUIModel(name ?: "", id, backdropPath ?: "")
        }

    private fun mapItemSeries(showResponse: MovieOrSeriesDetailResponse) = showResponse.run {
        CarrouselMoviesOrSeriesUIModel(name ?: "", id, posterPath ?: "")
    }

    private fun mapListItemSeries(
        gender: GenreResponse,
        list: List<MovieOrSeriesDetailResponse>
    ): List<CarrouselMoviesOrSeriesUIModel> {
        val showUIModelList = arrayListOf<CarrouselMoviesOrSeriesUIModel>()
        list.forEach { item ->
            if (item.genres?.any { it.id == gender.id } == true)
                showUIModelList.add(mapItemSeries(item))
        }
        return showUIModelList

    }
}