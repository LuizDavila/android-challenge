package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.ItemSeriesUIModel
import com.zygotecnologia.zygotv.data.model.SeriesListByGenderUIModel
import com.zygotecnologia.zygotv.data.model.SeriesUIModel
import com.zygotecnologia.zygotv.domain.entity.response.GenreListResponse
import com.zygotecnologia.zygotv.domain.entity.response.GenreResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse

object ShowUIMapper {

    fun map(genderList: GenreListResponse, list: List<ShowResponse>) = SeriesUIModel(
        getTopShow(list),
        mapSeriesListByGender(genderList.genres, list)
    )

    private fun mapSeriesListByGender(genderList: List<GenreResponse>?, list: List<ShowResponse>) =
        genderList?.distinct()?.map { gender ->
            SeriesListByGenderUIModel(
                gender.name ?: "",
                mapListItemSeries(gender, list))
        }


    private fun getTopShow(list: List<ShowResponse>) =
        list.maxByOrNull { it.voteCount ?: 0 }?.run {
            mapItemSeries(this)
        }

    private fun mapItemSeries(showResponse: ShowResponse) = showResponse.run {
        ItemSeriesUIModel(name ?: "", id, posterPath ?: "")
    }

    private fun mapListItemSeries(gender: GenreResponse, list: List<ShowResponse>): List<ItemSeriesUIModel> {
        val showUIModelList = arrayListOf<ItemSeriesUIModel>()
        list.forEach { item ->
            if( item.genres?.any { it.id == gender.id } == true)
                showUIModelList.add(mapItemSeries(item))
        }
        return showUIModelList

    }
}